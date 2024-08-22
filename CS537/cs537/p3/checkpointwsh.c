#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <fcntl.h>
#include <termios.h>
// #include <wsh.h>


#define MAX_INPUT_LENGTH 1024
#define MAX_ARGS 250
#define MAX_JOBS 250

static pid_t global_fg_pgid = -1; // Track the currently foreground process group ID

typedef struct Job {
    int id;
    char name[50];
    char* args[100];
    pid_t PID;
    int background;
} job;

static job* joblist[MAX_JOBS]; 

void handle_sigint(int signum) {
    if (global_fg_pgid != -1) {
        // Send SIGINT to the foreground process group
        kill(-global_fg_pgid, SIGINT);
    }
}

void handle_sigtstp(int signum) {
    if (global_fg_pgid != -1) {
        // Send SIGTSTP to the foreground process group
        kill(-global_fg_pgid, SIGTSTP);
    }
}




void handle_sigchld(int signum) {
    // Handle terminated child processes here (PREVENT ZOMMBIES)
    while (1) {
        int status;
        pid_t terminated_pid = waitpid(-1, &status, WNOHANG);
        
        if (terminated_pid <= 0) {
            // No more terminated child processes to reap
            break;
        }

        // Iterate through joblist to find and remove the corresponding job entry
        for (int i = 0; i < MAX_JOBS; i++) {
            if (joblist[i] != NULL && joblist[i]->PID == terminated_pid) {
                // Free the memory associated with the job
                free(joblist[i]);
                joblist[i] = NULL;
                break;  // Job found and removed, exit the loop
            }
        }
    }
}
// //Input/Output Redirection:
// // Input Redirection
int inputredirection(char **args, int arg_count) {
        for (int i = 0; i < arg_count; i++) {
            if (strcmp(args[i], "<") == 0) {
                if (i + 1 < arg_count) {
                    int input_fd = open(args[i + 1], O_RDONLY);
                    if (input_fd == -1) {
                        perror("open");
                        exit(EXIT_FAILURE);
                    }
                    if (dup2(input_fd, STDIN_FILENO) == -1) {
                        perror ("Failed to redirect standard output");
                        exit(EXIT_FAILURE);
                    }
                    close(input_fd);
                    args[i] = NULL;  // Remove "<" from the arguments
                    args[i + 1] = NULL;  // Remove the input file path
                } else {
                    perror("Missing input file after '<'");
                    exit(EXIT_FAILURE);
                }
                return i;
            }
        }
        return 0;
}
int outputredirection(char **args, int arg_count) {
        // Output Redirection
        for (int i = 0; i < arg_count; i++) {
            if (strcmp(args[i], ">") == 0) {
                if (i + 1 < arg_count) {
                    int output_fd = open(args[i + 1], O_WRONLY | O_CREAT | O_TRUNC, 0644);
                    if (output_fd == -1) {
                        perror("open redirection file");
                        exit(EXIT_FAILURE);
                    }
                    if (dup2(output_fd, STDOUT_FILENO) == -1) {
                        perror ("Failed to redirect standard output");
                        exit(EXIT_FAILURE);
                    }
                    close(output_fd);
                    printf("Hey");
                } else {
                    perror("Missing output file after '>'");
                    exit(EXIT_FAILURE);
                }
                return i;
            }
        }
        return 0;
}



static int printJobs() {
    
    int i = 0;
    if (joblist[2] != NULL) {
        printf("%d", joblist[2]->id);
    }
    for (i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL) {
            printf("%d: %s", joblist[i]->id, joblist[i]->name); //Print the id and name
            int j = 0;
            while (joblist[i]->args[j] != NULL) {
                if (j != 0) { //Skip the command arg
                    printf(" %s", joblist[i]->args[j]); //print the args
                }
                j++;
            }
            if (joblist[i]->background == 1) {
                printf(" &"); //Add bg tag
            }
            printf("\n");
        }
    }
    return 0;
}

pid_t get_pid_for_job_id(int job_id) {
    for (int i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL && joblist[i]->id == job_id) {
            return joblist[i]->PID;
        }
    }
    // Return -1 if the job ID is not found
    return -1;
}





int getLargestJobID() {
    int largestJobID = 0;

    for (int i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL && joblist[i]->id > largestJobID) {
            largestJobID = joblist[i]->id;
        }
    }

    return largestJobID;
}

int getNewJobID() {
    int smallestAvailableID = 1;

    // Iterate through the joblist to find the smallest available job ID
    for (int i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL && joblist[i]->id == smallestAvailableID) {
            // The job ID is already in use, increment the smallest available ID
            smallestAvailableID++;
            i = -1;  // Start the search from the beginning
        }
    }

    return smallestAvailableID;
}



int cd(char* folder) {
    return chdir(folder);
}



int main() {
    struct termios original_termios;
    tcgetattr(STDIN_FILENO, &original_termios);  // Store original terminal attributes
    signal(SIGTTOU, SIG_IGN);
    signal(SIGCHLD, handle_sigchld);
    signal(SIGTSTP, handle_sigtstp); // Handle Ctrl-Z
    signal(SIGINT, handle_sigint);   // Handle Ctrl-C

    while (1) {
        printf("wsh> ");
        char input[MAX_INPUT_LENGTH];
        if (fgets(input, sizeof(input), stdin) == NULL) {
            break;
        }

        
        input[strlen(input) - 1] = '\0';  // Remove newline character


        //Built in functions
        if (strcmp(input, "exit") == 0) {
                // Exit the shell
                //FREE JOBSLIST
                
                exit(0);
        }
        // Parse input
        char* args[MAX_ARGS];
        char* token = strtok(input, " ");
        int arg_count = 0;
        while (token != NULL) {
            args[arg_count++] = token;
            token = strtok(NULL, " ");
        }
        args[arg_count] = NULL;

        if (arg_count == 0) {
            continue;  // Empty command, prompt again skipping whole loop
        }


        



        if (strcmp(args[0], "cd") == 0) {
            // 
            if (arg_count != 2) {
                perror("Argument Error");
            }
            if (cd(args[1]) == -1) {
                perror("Error with chirdir");
            }
            continue;
        }

        if (strcmp(args[0], "jobs") == 0) {
            printJobs();
            continue;
        }

        if (strcmp(args[0], "fg") == 0) {
            int jobID;
            if (arg_count == 1) {
                jobID = getLargestJobID();
            } else {
                if (arg_count == 2) {
                    jobID = atoi(args[1]);
                } else {
                    printf("Only 1 argument allowed");
                }
            }

            if (kill(global_fg_pgid, SIGCONT) == -1) {
                    perror("kill");
                    continue;
            }
            //Search through job list and get PID
            int fgPID = get_pid_for_job_id(jobID);

            global_fg_pgid = fgPID;

            //Set the process group of the terminal to the given PID
            tcsetpgrp(STDIN_FILENO, fgPID);

            // Wait for the foreground process to complete
            int status;

            waitpid(fgPID, &status, WUNTRACED);
            // Set the process group of the terminal back to the shell
            tcsetpgrp(STDIN_FILENO, getpgrp());
            //Restore original terminal attributes
            tcgetattr(STDIN_FILENO, &original_termios);  // Restore original terminal attributes

            //Update job list
            for (int i = 0; i < MAX_JOBS; i++) {
                if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid) {
                    joblist[i]->background = 0;
                }
            }

            continue;
        }

        if (strcmp(args[0], "bg") == 0) {
            // Set to background in joblist
            for (int i = 0; i < MAX_JOBS; i++) {
                if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid) {
                    joblist[i]->background = 1;
                }
            }
            // Continue the background process
            if (kill(global_fg_pgid, SIGCONT) == -1) {
                perror("kill");
                continue;
            }
            continue;
        }



        //Background
        if (strcmp(args[arg_count - 1], "&") == 0) { //Child Process
            args[arg_count - 1] = NULL;  // Remove "&" from the arguments

            pid_t forkPID = fork();
            if (forkPID == 0) { 
                //Mute background
                int devnull = open("/dev/null", O_WRONLY);
                if (devnull == -1) {
                    perror("open");
                    exit(1);
                }
                dup2(devnull, STDOUT_FILENO);
                dup2(devnull, STDERR_FILENO);
                close(devnull);

                setpgid(0, 0);  // Set a new process group for the background process
                
                //Redirect input/output if necessary
                int didRedirectInput = inputredirection(args, arg_count);
                if (didRedirectInput > 0) {
                    // args[i + 1] = NULL;  // Remove the output file path
                    args[didRedirectInput] = NULL;  // Remove ">" from the arguments
                }
                int didRedirectOutput = outputredirection(args, arg_count);
                if (didRedirectOutput > 0) {
                    // args[i + 1] = NULL;  // Remove the output file path
                    args[didRedirectOutput] = NULL;  // Remove ">" from the arguments
                }
                execvp(args[0], args);
                perror("execvp");
                exit(1);
            } else if (forkPID > 0) { // Parent process
                //Create job
                int ID = getNewJobID();
                
                job *newJob = malloc(sizeof(*newJob));
                newJob->id = ID;
                newJob->background= 1;
                newJob->PID = forkPID;
                strcpy(newJob->name, args[0]);

                int i = 0;

                while (args[i] != NULL) {
                    newJob->args[i] = strdup(args[i]);
                    i++;
                }
                //Add job to joblist
                joblist[ID-1] = newJob;
            
            } else {
                perror("fork");
            }
        } 
        
        
        
        else {// Foreground execution
            pid_t forkPID = fork();
            if (forkPID == 0) {

                // Child process
                tcsetattr(STDIN_FILENO, TCSANOW, &original_termios);  // Restore original terminal attributes
                setpgid(0, 0);  // Set a new process group for the foreground process
                global_fg_pgid = getpid();
                //Redirect input/output if necessary
                int didRedirectInput = inputredirection(args, arg_count);
                if (didRedirectInput > 0) {
                    // args[i + 1] = NULL;  // Remove the output file path
                    args[didRedirectInput] = NULL;  // Remove ">" from the arguments
                }
                int didRedirectOutput = outputredirection(args, arg_count);
                if (didRedirectOutput > 0) {
                    // args[i + 1] = NULL;  // Remove the output file path
                    args[didRedirectOutput] = NULL;  // Remove ">" from the arguments
                }
                execvp(args[0], args);
                perror("execvp");
                exit(1);
            } else if (forkPID > 0) {
                // Parent process
                //Create job
                int ID = getNewJobID();
                job *newJob = malloc(sizeof(*newJob));
                newJob->id = ID;
                newJob->background = 0;
                newJob->PID = forkPID;
                strcpy(newJob->name, args[0]);
                int i;
                while (args[i] != NULL) {
                    newJob->args[i] = strdup(args[i]);
                    i++;
                }
                //Add job to joblist
                joblist[ID-1] = newJob;
                
                int status;
                tcsetpgrp(STDIN_FILENO, forkPID);  // Set the foreground process group
                waitpid(forkPID, &status, WUNTRACED);
                tcsetpgrp(STDIN_FILENO, getpgrp());  // Restore shell to the foreground process group
                tcgetattr(STDIN_FILENO, &original_termios);  // Restore original terminal attributes
            } else {
                perror("fork");
            }
        }
    }

    tcsetattr(STDIN_FILENO, TCSANOW, &original_termios);  // Restore original terminal attributes before exiting
    printf("\n");
    return 0;
}

/**
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <fcntl.h>
#include <termios.h>
// #include <wsh.h>


#define MAX_INPUT_LENGTH 1024
#define MAX_ARGS 250
#define MAX_JOBS 250

static pid_t global_fg_pgid = -1; // Track the currently foreground process group ID

typedef struct Job {
    int id;
    char name[50];
    char* args[100];
    pid_t PID;
    int background;
} job;

static job* joblist[MAX_JOBS]; 

void handle_sigint(int signum) {
    if (global_fg_pgid != -1) {
        // Send SIGINT to the foreground process group
        kill(-global_fg_pgid, SIGINT);
    }
}

void handle_sigtstp(int signum) {
    if (global_fg_pgid != -1) {
        // Send SIGTSTP to the foreground process group
        kill(-global_fg_pgid, SIGTSTP);
    }
}




void handle_sigchld(int signum) {
    // Handle terminated child processes here (PREVENT ZOMMBIES)
    while (1) {
        int status;
        pid_t terminated_pid = waitpid(-1, &status, WNOHANG);
        
        if (terminated_pid <= 0) {
            // No more terminated child processes to reap
            break;
        }

        // Iterate through joblist to find and remove the corresponding job entry
        for (int i = 0; i < MAX_JOBS; i++) {
            if (joblist[i] != NULL && joblist[i]->PID == terminated_pid) {
                // Free the memory associated with the job
                free(joblist[i]);
                joblist[i] = NULL;
                break;  // Job found and removed, exit the loop
            }
        }
    }
}



static int printJobs() {
    
    int i = 0;
    if (joblist[2] != NULL) {
        printf("%d", joblist[2]->id);
    }
    for (i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL) {
            printf("%d: %s", joblist[i]->id, joblist[i]->name); //Print the id and name
            int j = 0;
            while (joblist[i]->args[j] != NULL) {
                if (j != 0) { //Skip the command arg
                    printf(" %s", joblist[i]->args[j]); //print the args
                }
                j++;
            }
            if (joblist[i]->background == 1) {
                printf(" &"); //Add bg tag
            }
            printf("\n");
        }
        // }
    }
    return 0;
}

pid_t get_pid_for_job_id(int job_id) {
    for (int i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL && joblist[i]->id == job_id) {
            return joblist[i]->PID;
        }
    }
    // Return -1 if the job ID is not found
    return -1;
}


// Function to remove a job by setting it to NULL based on its job ID
// void remove_job_by_id(int job_id) {
//     for (int i = 0; i < MAX_JOBS; i++) {
//         if (joblist[i] != NULL && joblist[i]->id == job_id) {
//             free(joblist[i]);
//             joblist[i] = NULL;
//             return; // Job found and removed, exit the loop
//         }
//     }
//     // If job ID is not found, print an error message
//     printf("Job ID %d not found\n", job_id);
// }

int getLargestJobID() {
    int largestJobID = 0;

    for (int i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL && joblist[i]->id > largestJobID) {
            largestJobID = joblist[i]->id;
        }
    }

    return largestJobID;
}

int getNewJobID() {
    int smallestAvailableID = 1;

    // Iterate through the joblist to find the smallest available job ID
    for (int i = 0; i < MAX_JOBS; i++) {
        if (joblist[i] != NULL && joblist[i]->id == smallestAvailableID) {
            // The job ID is already in use, increment the smallest available ID
            smallestAvailableID++;
            i = -1;  // Start the search from the beginning
        }
    }

    return smallestAvailableID;
}



int cd(char* folder) {
    return chdir(folder);
}



int main() {
    struct termios original_termios;
    tcgetattr(STDIN_FILENO, &original_termios);  // Store original terminal attributes
    signal(SIGTTOU, SIG_IGN);
    signal(SIGCHLD, handle_sigchld);
    signal(SIGTSTP, handle_sigtstp); // Handle Ctrl-Z
    signal(SIGINT, handle_sigint);   // Handle Ctrl-C

    while (1) {
        printf("wsh> ");
        char input[MAX_INPUT_LENGTH];
        if (fgets(input, sizeof(input), stdin) == NULL) {
            break;
        }

        
        input[strlen(input) - 1] = '\0';  // Remove newline character


        //Built in functions
        if (strcmp(input, "exit") == 0) {
                // Exit the shell
                //FREE JOBSLIST
                
                exit(0);
        }
        // Parse input
        char* args[MAX_ARGS];
        char* token = strtok(input, " ");
        int arg_count = 0;
        while (token != NULL) {
            args[arg_count++] = token;
            token = strtok(NULL, " ");
        }
        args[arg_count] = NULL;

        if (arg_count == 0) {
            continue;  // Empty command, prompt again skipping whole loop
        }



        if (strcmp(args[0], "cd") == 0) {
            // 
            if (arg_count != 2) {
                perror("Argument Error");
            }
            if (cd(args[1]) == -1) {
                perror("Error with chirdir");
            }
            continue;
        }

        if (strcmp(args[0], "jobs") == 0) {
            printJobs();
            continue;
        }

        if (strcmp(args[0], "fg") == 0) {

            int jobID;
            if (arg_count == 1) {
                jobID = getLargestJobID();
            } else {
                if (arg_count == 2) {
                    jobID = atoi(args[1]);
                } else {
                    printf("Only 1 argument allowed");
                }
            }

            if (kill(global_fg_pgid, SIGCONT) == -1) {
                    perror("kill");
                    continue;
            }
            //Search through job list and get PID
            int fgPID = get_pid_for_job_id(jobID);

            global_fg_pgid = fgPID;




            //Set the process group of the terminal to the given PID
            tcsetpgrp(STDIN_FILENO, fgPID);

           



            // Wait for the foreground process to complete
            int status;

            waitpid(fgPID, &status, WUNTRACED);
            // Set the process group of the terminal back to the shell
            tcsetpgrp(STDIN_FILENO, getpgrp());
            //Restore original terminal attributes
            tcgetattr(STDIN_FILENO, &original_termios);  // Restore original terminal attributes

            //Update job list
            for (int i = 0; i < MAX_JOBS; i++) {
                if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid) {
                    joblist[i]->background = 0;
                }
            }

            
            continue;
        }

        if (strcmp(args[0], "bg") == 0) {

            // Set to background in joblist
            for (int i = 0; i < MAX_JOBS; i++) {
                if (joblist[i] != NULL && joblist[i]->PID == global_fg_pgid) {
                    joblist[i]->background = 1;
                }
            }

            // Continue the background process
            if (kill(global_fg_pgid, SIGCONT) == -1) {
                perror("kill");
                continue;
            }

            continue;
        }






        



        //Background
        if (strcmp(args[arg_count - 1], "&") == 0) {
            args[arg_count - 1] = NULL;  // Remove "&" from the arguments

            pid_t forkPID = fork();
            if (forkPID == 0) { 
                //Mute background
                int devnull = open("/dev/null", O_WRONLY);
                if (devnull == -1) {
                    perror("open");
                    exit(1);
                }
                dup2(devnull, STDOUT_FILENO);
                dup2(devnull, STDERR_FILENO);
                close(devnull);
                // Child process
                
                setpgid(0, 0);  // Set a new process group for the background process
                execvp(args[0], args);
                perror("execvp");
                exit(1);
            } else if (forkPID > 0) { 
                // Parent process
                //Create job

                int ID = getNewJobID();
                
                job *newJob = malloc(sizeof(*newJob));
                newJob->id = ID;
                newJob->background= 1;
                newJob->PID = forkPID;
                strcpy(newJob->name, args[0]);

                int i = 0;

                while (args[i] != NULL) {
                    newJob->args[i] = strdup(args[i]);
                    i++;
                }

                //Add job to joblist
                joblist[ID-1] = newJob;
                // printf("%d",joblist[ID-1]->id);

                // printJobs();
                // printf("%d", joblist[2]->id);
            
            
            } else {
                perror("fork");
            }
        } else {// Foreground execution

            pid_t forkPID = fork();
            if (forkPID == 0) {
                // Child process
                tcsetattr(STDIN_FILENO, TCSANOW, &original_termios);  // Restore original terminal attributes
                setpgid(0, 0);  // Set a new process group for the foreground process
                execvp(args[0], args);
                perror("execvp");
                global_fg_pgid = getpid();
                exit(1);
            } else if (forkPID > 0) {
                // Parent process

                //Create job
                int ID = getNewJobID();
                job *newJob = malloc(sizeof(*newJob));
                newJob->id = ID;
                newJob->background = 0;
                newJob->PID = forkPID;
                strcpy(newJob->name, args[0]);
                int i;
                while (args[i] != NULL) {
                    newJob->args[i] = strdup(args[i]);
                    i++;
                }
                //Add job to joblist
                joblist[ID-1] = newJob;
                

                int status;
                tcsetpgrp(STDIN_FILENO, forkPID);  // Set the foreground process group
                waitpid(forkPID, &status, WUNTRACED);
                tcsetpgrp(STDIN_FILENO, getpgrp());  // Restore shell to the foreground process group
                tcgetattr(STDIN_FILENO, &original_termios);  // Restore original terminal attributes
            } else {
                perror("fork");
            }
        }
    }

    tcsetattr(STDIN_FILENO, TCSANOW, &original_termios);  // Restore original terminal attributes before exiting
    printf("\n");
    return 0;
}
**/