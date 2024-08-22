#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <fcntl.h>
#include <termios.h>
#include <ctype.h>

#define MAX_INPUT_LENGTH 1024
#define MAX_ARGS 250
#define MAX_JOBS 250

static pid_t global_fg_pgid = -1; // Track the currently foreground process group ID

typedef struct Job
{
    int id;
    char name[50];
    char *args[100];
    pid_t PID;
    int background;
} job;

static job *joblist[MAX_JOBS];

void handle_sigint(int signum)
{
    if (global_fg_pgid != -1)
    {
        // Send SIGINT to the foreground process group
        kill(-global_fg_pgid, SIGINT);
    }
}

void handle_sigtstp(int signum)
{
    if (global_fg_pgid != -1)
    {
        // Send SIGTSTP to the foreground process group
        kill(-global_fg_pgid, SIGTSTP);
    }
}

void handle_sigchld(int signum)
{
    // Handle terminated child processes here (PREVENT ZOMMBIES)
    while (1)
    {
        int status;
        pid_t terminated_pid = waitpid(-1, &status, WNOHANG);

        if (terminated_pid <= 0)
        {
            // No more terminated child processes to reap
            break;
        }

        // Iterate through joblist to find and remove the corresponding job entry
        for (int i = 0; i < MAX_JOBS; i++)
        {
            if (joblist[i] != NULL && joblist[i]->PID == terminated_pid)
            {
                // Free the memory associated with the job
                free(joblist[i]);
                joblist[i] = NULL;
                break; // Job found and removed, exit the loop
            }
        }
    }
}

static int printJobs()
{

    int i = 0;
    for (i = 0; i < MAX_JOBS; i++)
    {
        if (joblist[i] != NULL)
        {
            printf("%d: %s", joblist[i]->id, joblist[i]->name); // Print the id and name
            int j = 0;
            while (joblist[i]->args[j] != NULL)
            {
                if (j != 0)
                {                                       // Skip the command arg
                    printf(" %s", joblist[i]->args[j]); // print the args
                }
                j++;
            }
            if (joblist[i]->background == 1)
            {
                printf(" &"); // Add bg tag
            }
            printf("\n");
        }
        // }
    }
    return 0;
}

pid_t get_pid_for_job_id(int job_id)
{
    for (int i = 0; i < MAX_JOBS; i++)
    {
        if (joblist[i] != NULL && joblist[i]->id == job_id)
        {
            return joblist[i]->PID;
        }
    }
    // Return -1 if the job ID is not found
    return -1;
}

void freeJob(job *j)
{
    if (j != NULL)
    {
        // Free dynamically allocated memory in the job structure
        for (int i = 0; i < 100; i++)
        {
            if (j->args[i] != NULL)
            {
                free(j->args[i]);
                j->args[i] = NULL;
            }
        }
        // Free the job structure itself
        free(j);
    }
}

int getLargestJobID()
{
    int largestJobID = 0;

    for (int i = 0; i < MAX_JOBS; i++)
    {
        if (joblist[i] != NULL && joblist[i]->id > largestJobID)
        {
            largestJobID = joblist[i]->id;
        }
    }

    return largestJobID;
}

int getNewJobID()
{
    int smallestAvailableID = 1;

    // Iterate through the joblist to find the smallest available job ID
    for (int i = 0; i < MAX_JOBS; i++)
    {
        if (joblist[i] != NULL && joblist[i]->id == smallestAvailableID)
        {
            // The job ID is already in use, increment the smallest available ID
            smallestAvailableID++;
            i = -1; // Start the search from the beginning
        }
    }

    return smallestAvailableID;
}

int cd(char *folder)
{
    return chdir(folder);
}

void trim(char *str)
{
    if (str == NULL)
    {
        return; // Handle NULL input
    }

    int len = strlen(str);

    // Trim trailing whitespace by moving the null terminator
    while (len > 0 && isspace(str[len - 1]))
    {
        len--;
    }
    str[len] = '\0';

    // Find the first non-whitespace character from the beginning
    int i = 0;
    while (i < len && isspace(str[i]))
    {
        i++;
    }

    // Shift the string to remove leading whitespace
    if (i > 0)
    {
        memmove(str, str + i, len - i + 1);
    }
}

void handledPiped(char *input)
{
    char *token;
    char *commands[10]; // Assuming a maximum of 10 commands
    int num_commands = 0;

    // Tokenize the input using strtok with '|'
    token = strtok(input, "|");
    while (token != NULL)
    {
        commands[num_commands++] = token;
        token = strtok(NULL, "|");
    }

    int prev_pipe[2];
    int curr_pipe[2];

    for (int i = 0; i < num_commands; i++)
    {
        // Create a new pipe for each command
        if (pipe(curr_pipe) == -1)
        {
            perror("pipe");
            exit(-1);
        }

        pid_t pid = fork();
        if (pid == -1)
        {
            perror("fork");
            exit(-1);
        }

        if (pid == 0)
        { // Child process
            // If not the first command, redirect input from the previous pipe
            if (i != 0)
            {
                dup2(prev_pipe[0], 0);
                close(prev_pipe[0]);
                close(prev_pipe[1]);
            }
            else
            { // First command, check for input redirection '<'
                char *input_redir = strchr(commands[i], '<');
                if (input_redir != NULL)
                {
                    // Split the command into command and input file
                    *input_redir = '\0';
                    char *input_file = input_redir + 1;
                    trim(input_file);
                    printf("%s", input_file);
                    int fd = open(input_file, O_RDONLY);
                    if (fd == -1)
                    {
                        perror("open");
                        exit(-1);
                    }
                    dup2(fd, 0);
                    close(fd);
                }
            }

            // If not the last command, redirect output to the current pipe
            if (i != num_commands - 1)
            {
                dup2(curr_pipe[1], 1);
                close(curr_pipe[0]);
                close(curr_pipe[1]);
            }
            else
            { // Last command, check for output redirection '>'
                char *output_redir = strchr(commands[i], '>');
                if (output_redir != NULL)
                {
                    // Split the command into command and output file
                    *output_redir = '\0';
                    char *output_file = output_redir + 1;
                    trim(output_file);
                    int fd = open(output_file, O_WRONLY | O_CREAT | O_TRUNC, 0644);
                    if (fd == -1)
                    {
                        perror("open");
                        exit(-1);
                    }
                    dup2(fd, 1);
                    close(fd);
                }
            }

            // Parse the command and execute it
            char *args[64]; // Assuming a maximum of 64 arguments
            int num_args = 0;

            token = strtok(commands[i], " \t\n");
            while (token != NULL)
            {
                args[num_args++] = token;
                token = strtok(NULL, " \t\n");
            }

            args[num_args] = NULL;

            execvp(args[0], args);
            perror("execvp"); // execvp only returns on error
            exit(-1);
        }
        else
        { // Parent process
            // Close the previous pipe's file descriptors
            if (i != 0)
            {
                close(prev_pipe[0]);
                close(prev_pipe[1]);
            }

            // Save the current pipe's file descriptors for the next iteration
            prev_pipe[0] = curr_pipe[0];
            prev_pipe[1] = curr_pipe[1];
        }
    }

    // Close the last pipe's file descriptors in the parent
    close(curr_pipe[0]);
    close(curr_pipe[1]);

    // Wait for all child processes to complete
    for (int i = 0; i < num_commands; i++)
    {
        wait(NULL);
    }
}

int main(int argc, char *argv[])
{
    struct termios original_termios;
    tcgetattr(STDIN_FILENO, &original_termios); // Store original terminal attributes
    signal(SIGTTOU, SIG_IGN);
    signal(SIGCHLD, handle_sigchld);
    signal(SIGTSTP, handle_sigtstp); // Handle Ctrl-Z
    signal(SIGINT, handle_sigint);   // Handle Ctrl-C

    if (argc == 1)
    {
        while (1)
        {
            printf("wsh> ");
            char input[MAX_INPUT_LENGTH];
            if (fgets(input, sizeof(input), stdin) == NULL)
            {
                break; // Check if EOF (Ctrl-D is used)
            }

            // Check if piped
            if (strchr(input, '|') != NULL || strchr(input, '<') != NULL || strchr(input, '>') != NULL)
            {
                handledPiped(input);
                continue;
            }

            input[strlen(input) - 1] = '\0'; // Remove newline character

            // Parse input
            char *args[MAX_ARGS];
            char *token = strtok(input, " ");
            int arg_count = 0;
            while (token != NULL)
            {
                args[arg_count++] = token;
                token = strtok(NULL, " ");
            }
            args[arg_count] = NULL;

            if (arg_count == 0)
            {
                continue; // Empty command, prompt again skipping whole loop
            }

            // Built in functions
            if (strcmp(args[0], "exit") == 0)
            {
                // Exit the shell
                tcsetattr(STDIN_FILENO, TCSANOW, &original_termios); // Restore original terminal attributes before exiting
                // Free List
                for (int i = 0; i < MAX_JOBS; i++)
                {
                    if (joblist[i] != NULL)
                    {
                        freeJob(joblist[i]);
                        joblist[i] = NULL;
                    }
                }
                exit(0);
            }

            if (strcmp(args[0], "cd") == 0)
            {
                //
                if (arg_count != 2)
                {
                    perror("Argument Error");
                }
                if (cd(args[1]) == -1)
                {
                    perror("Error with chirdir");
                }
                continue;
            }

            if (strcmp(args[0], "jobs") == 0)
            {
                printJobs();
                continue;
            }

            if (strcmp(args[0], "fg") == 0)
            {

                int jobID;
                if (arg_count == 1)
                {
                    jobID = getLargestJobID();
                }
                else
                {
                    if (arg_count == 2)
                    {
                        jobID = atoi(args[1]);
                    }
                    else
                    {
                        printf("Only 1 argument allowed");
                    }
                }
                // Search through job list and get PID
                int fgPID = get_pid_for_job_id(jobID);

                global_fg_pgid = fgPID;

                if (kill(global_fg_pgid, SIGCONT) == -1)
                {
                    perror("kill");
                    continue;
                }
                

                // Set the process group of the terminal to the given PID
                tcsetpgrp(STDIN_FILENO, fgPID);

                // Wait for the foreground process to complete
                int status;

                waitpid(fgPID, &status, WUNTRACED);
                // Set the process group of the terminal back to the shell
                tcsetpgrp(STDIN_FILENO, getpgrp());
                // Restore original terminal attributes
                tcgetattr(STDIN_FILENO, &original_termios); // Restore original terminal attributes

                // Update job list
                for (int i = 0; i < MAX_JOBS; i++)
                {
                    if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid)
                    {
                        joblist[i]->background = 0;
                    }
                }

                continue;
            }

            if (strcmp(args[0], "bg") == 0)
            {

                // Set to background in joblist
                for (int i = 0; i < MAX_JOBS; i++)
                {
                    if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid)
                    {
                        joblist[i]->background = 1;
                    }
                }

                // Continue the background process
                if (kill(global_fg_pgid, SIGCONT) == -1)
                {
                    perror("kill");
                    continue;
                }

                continue;
            }

            // Other commands
            if (strcmp(args[arg_count - 1], "&") == 0)
            {
                args[arg_count - 1] = NULL; // Remove "&" from the arguments

                pid_t forkPID = fork();
                if (forkPID == 0)
                {
                    // Mute background
                    int devnull = open("/dev/null", O_WRONLY);
                    if (devnull == -1)
                    {
                        perror("open");
                        exit(-1);
                    }
                    dup2(devnull, STDOUT_FILENO);
                    dup2(devnull, STDERR_FILENO);
                    close(devnull);
                    // Child process

                    setpgid(0, 0); // Set a new process group for the background process
                    execvp(args[0], args);
                    perror("execvp");
                    exit(-1);
                }
                else if (forkPID > 0)
                {
                    // Parent process
                    // Create job

                    int ID = getNewJobID();

                    job *newJob = malloc(sizeof(*newJob));
                    newJob->id = ID;
                    newJob->background = 1;
                    newJob->PID = forkPID;
                    strcpy(newJob->name, args[0]);

                    int i = 0;

                    while (args[i] != NULL)
                    {
                        newJob->args[i] = strdup(args[i]);
                        i++;
                    }

                    // Add job to joblist
                    joblist[ID - 1] = newJob;
                }
                else
                {
                    perror("fork");
                }
            }
            else
            { // Foreground execution

                pid_t forkPID = fork();
                if (forkPID == 0)
                {
                    // Child process
                    tcsetattr(STDIN_FILENO, TCSANOW, &original_termios); // Restore original terminal attributes
                    setpgid(0, 0);                                       // Set a new process group for the foreground process
                    execvp(args[0], args);
                    perror("execvp");
                    global_fg_pgid = getpid();
                    exit(-1);
                }
                else if (forkPID > 0)
                {
                    // Parent process

                    // Create job
                    int ID = getNewJobID();
                    job *newJob = malloc(sizeof(*newJob));
                    newJob->id = ID;
                    newJob->background = 0;
                    newJob->PID = forkPID;
                    strcpy(newJob->name, args[0]);
                    int i = 0;
                    while (args[i] != NULL)
                    {
                        newJob->args[i] = strdup(args[i]);
                        i++;
                    }
                    newJob->args[i] = NULL;
                    // Add job to joblist
                    joblist[ID - 1] = newJob;

                    int status;
                    tcsetpgrp(STDIN_FILENO, forkPID); // Set the foreground process group
                    waitpid(forkPID, &status, WUNTRACED);
                    tcsetpgrp(STDIN_FILENO, getpgrp());         // Restore shell to the foreground process group
                    tcgetattr(STDIN_FILENO, &original_termios); // Restore original terminal attributes
                }
                else
                {
                    perror("fork");
                }
            }
        }
    }
    else
    {
        if (argc == 2)
        { // Batch mode

            FILE *batch_file = fopen(argv[1], "r+");
            if (batch_file == NULL)
            {
                perror("Error opening batch file");
                return 1;
            }

            char input[MAX_INPUT_LENGTH];
            while (fgets(input, sizeof(input), batch_file) != NULL)
            {
                // Check if piped
                if (strchr(input, '|') != NULL || strchr(input, '<') != NULL || strchr(input, '>') != NULL)
                {
                    handledPiped(input);
                    continue;
                }

                input[strlen(input) - 1] = '\0'; // Remove newline character

                // Parse input
                char *args[MAX_ARGS];
                char *token = strtok(input, " ");
                int arg_count = 0;
                while (token != NULL)
                {
                    args[arg_count++] = token;
                    token = strtok(NULL, " ");
                }
                args[arg_count] = NULL;

                if (arg_count == 0)
                {
                    continue; // Empty command, prompt again skipping whole loop
                }

                // Built in functions
                if (strcmp(args[0], "exit") == 0)
                {
                    // Exit the shell
                    tcsetattr(STDIN_FILENO, TCSANOW, &original_termios); // Restore original terminal attributes before exiting
                    // Free List
                    for (int i = 0; i < MAX_JOBS; i++)
                    {
                        if (joblist[i] != NULL)
                        {
                            freeJob(joblist[i]);
                            joblist[i] = NULL;
                        }
                    }
                    exit(0);
                }

                if (strcmp(args[0], "cd") == 0)
                {
                    //
                    if (arg_count != 2)
                    {
                        perror("Argument Error");
                    }
                    if (cd(args[1]) == -1)
                    {
                        perror("Error with chirdir");
                    }
                    continue;
                }

                if (strcmp(args[0], "jobs") == 0)
                {
                    printJobs();
                    continue;
                }

                if (strcmp(args[0], "fg") == 0)
                {

                    int jobID;
                    if (arg_count == 1)
                    {
                        jobID = getLargestJobID();
                    }
                    else
                    {
                        if (arg_count == 2)
                        {
                            jobID = atoi(args[1]);
                        }
                        else
                        {
                            printf("Only 1 argument allowed");
                        }
                    }

                    if (kill(global_fg_pgid, SIGCONT) == -1)
                    {
                        perror("kill");
                        continue;
                    }
                    // Search through job list and get PID
                    int fgPID = get_pid_for_job_id(jobID);

                    global_fg_pgid = fgPID;

                    // Set the process group of the terminal to the given PID
                    tcsetpgrp(STDIN_FILENO, fgPID);

                    // Wait for the foreground process to complete
                    int status;

                    waitpid(fgPID, &status, WUNTRACED);
                    // Set the process group of the terminal back to the shell
                    tcsetpgrp(STDIN_FILENO, getpgrp());
                    // Restore original terminal attributes
                    tcgetattr(STDIN_FILENO, &original_termios); // Restore original terminal attributes

                    // Update job list
                    for (int i = 0; i < MAX_JOBS; i++)
                    {
                        if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid)
                        {
                            joblist[i]->background = 0;
                        }
                    }

                    continue;
                }

                if (strcmp(args[0], "bg") == 0)
                {

                    // Set to background in joblist
                    for (int i = 0; i < MAX_JOBS; i++)
                    {
                        if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid)
                        {
                            joblist[i]->background = 1;
                        }
                    }

                    // Continue the background process
                    if (kill(global_fg_pgid, SIGCONT) == -1)
                    {
                        perror("kill");
                        continue;
                    }

                    continue;
                }

                // Other commands

                pid_t forkPID = fork();
                if (forkPID == 0)
                {
                    // Child process
                    tcsetattr(STDIN_FILENO, TCSANOW, &original_termios); // Restore original terminal attributes
                    setpgid(0, 0);                                       // Set a new process group for the foreground process
                    execvp(args[0], args);
                    perror("execvp");
                    global_fg_pgid = getpid();
                    exit(-1);
                }
                else if (forkPID > 0)
                {
                    // Parent process

                    // Create job
                    int ID = getNewJobID();
                    job *newJob = malloc(sizeof(*newJob));
                    newJob->id = ID;
                    newJob->background = 0;
                    newJob->PID = forkPID;
                    strcpy(newJob->name, args[0]);
                    int i = 0;
                    while (args[i] != NULL)
                    {
                        newJob->args[i] = strdup(args[i]);
                        i++;
                    }
                    // Add job to joblist
                    joblist[ID - 1] = newJob;

                    int status;
                    tcsetpgrp(STDIN_FILENO, forkPID); // Set the foreground process group
                    waitpid(forkPID, &status, WUNTRACED);
                    tcsetpgrp(STDIN_FILENO, getpgrp());         // Restore shell to the foreground process group
                    tcgetattr(STDIN_FILENO, &original_termios); // Restore original terminal attributes
                }
                else
                {
                    perror("fork");
                }
            }
            fclose(batch_file);
        }
        else
        { // invalid amount of args
            printf("Wrong number of inputs");
            exit(-1);
        }
    }

    // Exit the shell
    tcsetattr(STDIN_FILENO, TCSANOW, &original_termios); // Restore original terminal attributes before exiting
    // Free List
    for (int i = 0; i < MAX_JOBS; i++)
    {
        if (joblist[i] != NULL)
        {
            freeJob(joblist[i]);
            joblist[i] = NULL;
        }
    }
    exit(0);
    return 0;
}
