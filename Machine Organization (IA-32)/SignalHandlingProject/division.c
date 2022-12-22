////////////////////////////////////////////////////////////////////////////////
// Main File:        mySigHandler.c
// This File:        division.c
// Other Files:      sendSig.c, mySigHandler.c
// Semester:         CS 354 Lecture 002 Fall 2022
// Instructor:       deppeler
//
// Author:           Aneesh Pandoh
// Email:            pandoh@wisc.edu
// CS Login:         pandoh
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////


#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>



int divcount = 0;

//Divide by 0 handler
void handler_SIGFPE() {
	printf("Error: a division by 0 operation was attemped.\n");
	printf("Total number of operations completed successfully: %i\n", divcount);
	printf("The program will be terminated.\n");
	exit(0);
}

//CTRL-C handler (Interrupt)
void  handler_SIGINT (int sig) {
	printf("\nTotal number of operations completed successfully: %i\n", divcount);
	printf("The program will be terminated.\n");
	exit(0);
}


//Main function for dividing 2 nums and getting result and remainder
int main(int argc, char *argv[]) {

	//divide by 0 handler binding
	struct sigaction s0;
	memset (&s0, 0, sizeof(s0));
	s0.sa_handler = handler_SIGFPE;
	if (sigaction(SIGFPE, &s0, NULL) < 0) {
		perror("Error handling");
		exit(1);
	}
	//Ctrl-C handler binding
	struct sigaction sc;
	memset (&sc, 0, sizeof(sc));
	sc.sa_handler = handler_SIGINT;
	if (sigaction (SIGINT, &sc, NULL) < 0) {
		perror ("Error handling");
		exit(1);
	}


	//Infinite loop of dividing
	while(1) {
		char buffer [1000];
		printf("Enter first integer: ");
		if (fgets(buffer, 100, stdin) == NULL) {
			perror ("Error fgets");
                	exit(1);
		}
		int num1 = atoi(buffer);
		printf("Enter second integer: ");
		if (fgets(buffer, 100, stdin) == NULL) {
                        perror ("Error fgets");
                        exit(1);
                }
		int num2 = atoi(buffer);
		int result = num1 / num2;
		int remainder = num1 % num2;
		//print resulting info
		printf("%i / %i is %i with a remainder of %i\n", num1, num2, result, remainder);
		//increment amount of divisions
		divcount++;
	}



	return 0;
}
