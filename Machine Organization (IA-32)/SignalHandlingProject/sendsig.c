////////////////////////////////////////////////////////////////////////////////
// Main File:        mySigHandler.c
// This File:        sendsig.c
// Other Files:      sendsig.c, division.c
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
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//Main function which sends interrupt and USR1 signal
int main(int argc, char *argv[]) {
	//Check amount of args
	if (argc != 3) {
		printf("Usage: <signal type> <pid>\n");
		exit(1);
	}

	pid_t pid = atoi(argv[2]); 

	//send INT signal (Ctrl-C)
	if (strcmp(argv[1],"-i")==0) {
		if (kill(pid, SIGINT) == -1) {
			perror("Error sending signal");
			exit(1);
		}
	}

	//send USR1 signal
	else if (strcmp(argv[1],"-u")==0) {
		if (kill(pid, SIGUSR1) == -1) {
			perror("Error sending signal");
			exit(1);
		}
	}

	//Incorrect signal type
	else {
		perror("Incorrect signal type");
		exit(1);
	}

	return 0;
}
