///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2021 Deb Deppeler
// Posting or sharing this file is prohibited, including any changes/additions.
//
// We have provided comments and structure for this program to help you get 
// started.  Later programs will not provide the same level of commenting,
// rather you will be expected to add same level of comments to your work.
// 09/20/2021 Revised to free memory allocated in get_board_size function.
// 01/24/2022 Revised to use pointers for CLAs
//
////////////////////////////////////////////////////////////////////////////////
// Main File:        check_board.c checks if a given board is a valid sudoku board 
// This File:        check_board.c
// Other Files:      
// Semester:         CS 354 Fall 2022
// Instructor:       deppeler
//
// Author:           Aneesh Pandoh
// Email:            pandoh@wisc.edu
// CS Login:         pandoh
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   Fully acknowledge and credit all sources of help,
//                   including Peer Mentors, Instructors, and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *DELIM = ",";  // commas ',' are a common delimiter character for data strings

/* COMPLETED (DO NOT EDIT):       
 * Read the first line of input file to get the size of that board.
 * 
 * PRE-CONDITION #1: file exists
 * PRE-CONDITION #2: first line of file contains valid non-zero integer value
 *
 * fptr: file pointer for the board's input file
 * size: a pointer to an int to store the size
 */
void get_board_size(FILE *fptr, int *size) {      
	char *line1 = NULL;
	size_t len = 0;

	if ( getline(&line1, &len, fptr) == -1 ) {
		printf("Error reading the input file.\n");
		free(line1);
		exit(1);
	}

	char *size_chars = NULL;
	size_chars = strtok(line1, DELIM);
	*size = atoi(size_chars);

	// free memory allocated for reading first link of file
	free(line1);
	line1 = NULL;
}



/* TODO:
 * Returns 1 if and only if the board is in a valid Sudoku board state.
 * Otherwise returns 0.
 * 
 * with no duplicate digits, where size is the value 1 to 9.
 * 
 * Note: p2A requires only that each row and each column are valid.
 * 
 * board: heap allocated 2D array of integers 
 * size:  number of rows and columns in the board
 */
int valid_board(int **board, int size) {
	//Rows
	int *row = malloc(sizeof(int)*size);//stores the values of a row to see if there are duplicates
	//row = 0;
	for (int i = 0; i < size; i++){
		//        int *row = malloc(sizeof(int)*size);//stores the values of a row to see if there are duplicates

		for (int i = 0; i < size; ++i){//can also use calloc
                        *(row+i) = 0;
                }

		for (int j = 0; j < size; j++) {
            //Check that the digits are the int
            if(sizeof(*(*board+i) + j)) != sizeof(int){
                free(row);
                row=NULL;
                return 0;
            }
            
            
			//check that the digits is only 0-9
			if((*(*(board+i)+j)) < 0 || (*(*(board+i)+j)) > 9 ){
				free(row);
				row=NULL;
				return 0;
			}
			//Checks if the int has already appeard in the row (not including 0)
			if (((*(*(board+i)+j)) != 0) && 
					(*(row+(*(*(board+i)+j))-1) == 1)) {//subtract 1 because of 0 index
				free(row);
				row=NULL;	
				return 0;
			} else if ((*(*(board+i)+j)) != 0) {//adds that the value is found in temporary row array
				*(row+(*(*(board+i)+j))-1) = 1; //Subtract 1 because because of 0 index
			}
		}
	}
	free(row);
	row = NULL;




	//Columns

		int *column = malloc(sizeof(int)*size);//stores the values of a column to see if there are duplicates

	for (int i = 0; i < size; i++){
	//	int *column = malloc(sizeof(int)*size);//stores the values of a column to see if there are duplicates

		for (int i = 0; i < size; ++i){//can also use calloc
                        *(column+i) = 0;
                }
		for (int j = 0; j < size; j++) {
			//Checks if the int has already appeard in the column (not including 0)
			if (((*(*(board+j)+i)) != 0) &&
				       	(*(column+(*(*(board+j)+i))-1) == 1)) {//subtract 1 because of 0 index
				free(column);
				column=NULL;
				return 0;
			} else if ((*(*(board+j)+i)) !=0) {//adds that the value is found to temporary column array
				*(column+(*(*(board+j)+i))-1) = 1; //Subtract 1 because because of 0 index
			}
		}
	}
	free(column);
        column = NULL;



	return 1;   
}    



/* TODO: COMPLETE THE MAIN FUNCTION
 * This program prints "valid" (without quotes) if the input file contains
 * a valid state of a Sudoku puzzle board wrt to rows and columns only.
 *
 * A single CLA which is the name of the file that contains board data 
 * is required.
 *
 * argc: the number of command line args (CLAs)
 * argv: the CLA strings, includes the program name
 */
int main( int argc, char **argv ) {              

	// TODO: Check if number of command-line arguments is correct.
	if (argc != 2) {
		printf("Only need 1 CLA argument.\n");
		exit(1);
	}

	// Open the file and check if it opened successfully.
	FILE *fp = fopen(*(argv + 1), "r");
	if (fp == NULL) {
		printf("Can't open file for reading.\n");
		exit(1);
	}

	// Declare local variables.
	int size;

	// TODO: Call get_board_size to read first line of file as the board size.
	get_board_size(fp, &size);

	// TODO: Dynamically allocate a 2D array for given board size.
	int **board = malloc(sizeof(int*)*size);

	// Read the rest of the file line by line.
	// Tokenize each line wrt the delimiter character 
	// and store the values in your 2D array.
	char *line = NULL;
	size_t len = 0;
	char *token = NULL;
	for (int i = 0; i < size; i++) {

		if (getline(&line, &len, fp) == -1) {
			printf("Error while reading line %i of the file.\n", i+2);
			free(line);
			exit(1);
		}
		*(board+i) = malloc(sizeof(int)*size);

		token = strtok(line, DELIM);
		for (int j = 0; j < size; j++) {
			// TODO: Complete the line of code below
			// to initialize your 2D array.
			/* ADD ARRAY ACCESS CODE HERE */
			*(*(board+i)+j) = atoi(token);

			token = strtok(NULL, DELIM);
		}
	}


	// TODO: Call the function valid_board and print the appropriate
	//       output depending on the function's return value.
	if (valid_board(board,size)) {
		printf("valid\n");
	} else {
		printf("invalid\n");
	}

	// TODO: Free all dynamically allocated memory.
	for (int i = 0; i < size; i++) {
		free(*(board+i));
	}
	free(board);
	board = NULL;
	free(line);
	line = NULL;



	//Close the file.
	if (fclose(fp) != 0) {
		printf("Error while closing the file.\n");
		exit(1);
	} 


	return 0;       
}       






// s22

