///////////////////////////////////////////////////////////////////////////////
// Copyright 2020 Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission, CS 354 Spring 2022, Deb Deppeler
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// Main File:        myMagicSquare.c
// This File:        myMagicSquare.c
// Other Files:      output.txt
// Semester:         CS 354 Lecture 00? Fall 2022
// Instructor:       deppeler
// 
// Author:           Aneesh Pandoh
// Email:            pandoh@wisc.edu
// CS Login:         pandoh
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//N/A

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure that represents a magic square
typedef struct {
	int size;           // dimension of the square
	int **magic_square; // pointer to heap allocated magic square
} MagicSquare;

/* TODO:
 * Prompts the user for the magic square's size, reads it,
 * checks if it's an odd number >= 3 (if not display the required
 * error message and exit), and returns the valid number.
 */
int getSize() {
	int size;
	printf("Enter magic square's size (odd integer >=3)\n");
	scanf("%i",&size);
	if (3 > size) {
		printf("Magic square size must be >= 3.\n");
		exit(1);
	}
	if (size % 2 == 0) {
		printf("Magic square size must be odd.\n");
		exit(1);
	}

	return size;   
} 

/* TODO:
 * Makes a magic square of size n using the alternate 
 * Siamese magic square algorithm from assignment and 
 * returns a pointer to the completed MagicSquare struct.
 *
 * n the number of rows and columns
 */
MagicSquare *generateMagicSquare(int n) {
	MagicSquare* result = malloc(sizeof(MagicSquare)); //Creates pointer to struct for faster copying or passing
	result->size = n;

	int** matrix = malloc(sizeof(int*)*n);
	for (int i = 0; i < n; ++i){
		*(matrix+i) = calloc(n, sizeof(int));	
	}

	//Regular Siamese Method
	int i = 0; //1st row
	int j = n/2; //center column



	//	printf("hello");
	for (int num = 1; num <= n*n; num++) {
		*(*(matrix+i)+j) = num;//Default, put number in spot
		i--;//Down 1 row
		j++;//Right 1 column

		// if spot is taken
		if (num % n == 0) { 
			i += 2; 
			j--; 
		}
		//if off the board
		else {
			if (i<0) { //row
				i += n;
			}
			else if (j==n) {//column
				j -= n;
			}
		}
	}



	result->magic_square = matrix;


	return result;    
}


/* TODO:  
 * Opens a new file (or overwrites the existing file)
 * and writes the square in the specified format.
 *
 * magic_square the magic square to write to a file
 * filename the name of the output file
 */
void fileOutputMagicSquare(MagicSquare *magic_square, char *filename) {
	FILE *outputFile;
	outputFile = fopen(filename, "w");
	if(outputFile == NULL){
		exit(1);
	}
	int** square = magic_square->magic_square;
	int size = magic_square->size;
	fprintf(outputFile,"%i\n", size);
	//Printing out each value
	for(int i = 0; i < size; i++){
		for(int j = 0; j < size; j++){
			if (j != (size-1)) 
				fprintf(outputFile,"%i%s", *(*(square + i) + j), ",");
			else 
				fprintf(outputFile,"%i", *(*(square + i) + j));
		}
		fprintf(outputFile,"%s","\n");
	}
	fclose(outputFile);

	//free everything
	for(int i = 0; i < size; i++) {
		free(*(square+i));
	}
	free(square);
	square = NULL;
	free(magic_square);
	magic_square = NULL;
	//free(outputFile);
	//outputFile = null;
}

/* TODO:
 * Generates a magic square of the user specified size and
 * output the quare to the output filename
 *
 * Add description of required CLAs here
 */
int main(int argc, char **argv) {
	// TODO: Check input arguments to get output filename
	if (2 != argc) {
		printf("Error");
		exit(1);
	}

	char* outputFile = *(argv + 1);
	// TODO: Get magic square's size from user
	int size = getSize();


	// TODO: Generate the magic square
	MagicSquare* square = generateMagicSquare(size); //store return in var



	// TODO: Output the magic square
	fileOutputMagicSquare(square, outputFile);
	//free(outputFile);
	//outputFile = NULL;

	return 0;
} 


//     F22 deppeler myMagicSquare.c      
