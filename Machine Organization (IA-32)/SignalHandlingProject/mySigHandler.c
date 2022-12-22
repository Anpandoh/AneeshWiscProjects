////////////////////////////////////////////////////////////////////////////////
// Main File:        mySigHandler.c
// This File:        mySigHandler.c
// Other Files:      sendSig.c, division.c
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
//
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>

int globalSeconds = 4;
int globalCounter = 0;

//Alarm singal handler
void handler_SIGALRM(){
	time_t now;
	if (time(&now) == -1) {
		perror("Error retrieving time");
		exit(1);
	}
	if (ctime(&now) == NULL) {
		perror("Error retrieving time");
                exit(1);
	}
	printf("PID: %i CURRENT TIME: %s", getpid(), ctime(&now)); 
	if (alarm(globalSeconds) != 0){
		perror("Error with repeating alarm");
		exit(1);
	}
}

//User signal handler
void handler_SIGUSR1() {
	printf("SIGUSR1 handled and counted!\n");
	globalCounter++;
}


//Ctrl - C Handler
void handler_SIGINT(){
	printf("\nSIGINT handled.\n");
	printf("SIGUSR1 was handled %i times. Exiting now.\n", globalCounter);
	exit(0);
}

//Main function which sends an alarm signal every 4 seconds which returns PID and time
int main(int argc, char *argv[]){
	//Send initial alarm signal
	printf("PID and time print every 4 seconds.\nType Ctrl-C to end the program.\n");
	alarm(globalSeconds);	

	//alarm handler binding
	struct sigaction sa;
	memset(&sa, 0, sizeof(sa));
	sa.sa_handler = handler_SIGALRM;
	if (sigaction(SIGALRM, &sa, NULL) != 0) {
		printf("Error:binding SIGALARM Handler\n");
		exit(1);
	}

	//user handler binding
	struct sigaction su;
	memset(&su, 0, sizeof(su));
	su.sa_handler = handler_SIGUSR1;
	if (sigaction(SIGUSR1, &su, NULL) != 0) {
		printf("Error:binding SIGUSR1 Handler\n");
		exit(1);
	}

	//Ctrl-C handler binding
	struct sigaction sc;
	memset(&sc, 0, sizeof(sc));
	sc.sa_handler = handler_SIGINT;
	if (sigaction(SIGINT, &sc, NULL) != 0) {
		printf("Error:binding SIGINT Handler\n");
		exit(1);
	}


	//Infinite Loop
	while (1){

	}
	return 0;
}
