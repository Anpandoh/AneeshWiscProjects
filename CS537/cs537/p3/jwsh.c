#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_INPUT_SIZE 1024
#define MAX_ARGS 64

void handledPiped(char *input) {
    // Implement the handling of piped commands here
}

int main(int argc, char* argv[]) {
    char input[MAX_INPUT_SIZE];
    FILE *batch_file = fopen(argv[1], "r"); // Replace with your input file

    if (batch_file == NULL) {
        perror("Error opening file");
        return 1;
    }

    while (fgets(input, sizeof(input), batch_file) != NULL) {
        // Check if piped or contains redirection symbols
        if (strchr(input, '|') != NULL || strchr(input, '<') != NULL || strchr(input, '>') != NULL) {
            handledPiped(input);
            continue;
        }

        input[strcspn(input, "\n")] = '\0'; // Remove newline character

        char *args[MAX_ARGS];
        char *token = strtok(input, " ");
        int arg_count = 0;

        while (token != NULL && arg_count < MAX_ARGS - 1) {
            args[arg_count++] = token;
            token = strtok(NULL, " ");
        }

        args[arg_count] = NULL;

        if (arg_count == 0) {
            continue; // Empty command, skip the rest of the loop
        }

        // Printing each argument
        for (int i = 0; i < arg_count; i++) {
            printf("args[%d] = %s\n", i, args[i]);
        }
    }

    fclose(batch_file);
    return 0;
}
