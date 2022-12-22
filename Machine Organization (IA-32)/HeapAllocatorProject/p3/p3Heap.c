///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2022 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission Fall 2022, CS354-deppeler
//
///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Main File:        p3Heap.c - allocates, frees, and coalesce memory in the heap, also displays the heap
// This File:        p3Heap.c
// Other Files:      tests files in ./tests folder and p3heap.h
// Semester:         CS 354 Lecture 00? Fall 2022
// Instructor:       deppeler
// 
// Author:           Aneesh Pandoh
// Email:            Pandoh@wisc.edu
// CS Login:         pandoh
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
//					N/A
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////



#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <stdio.h>
#include <string.h>
#include "p3Heap.h"

/*
 * This structure serves as the header for each allocated and free block.
 * It also serves as the footer for each free block but only containing size.
 */
typedef struct blockHeader {           

	int size_status;

	/*
	 * Size of the block is always a multiple of 8.
	 * Size is stored in all block headers and in free block footers.
	 *
	 * Status is stored only in headers using the two least significant bits.
	 *   Bit0 => least significant bit, last bit
	 *   Bit0 == 0 => free block
	 *   Bit0 == 1 => allocated block
	 *
	 *   Bit1 => second last bit 
	 *   Bit1 == 0 => previous block is free
	 *   Bit1 == 1 => previous block is allocated
	 * 
	 * End Mark: 
	 *  The end of the available memory is indicated using a size_status of 1.
	 * 
	 * Examples:
	 * 
	 * 1. Allocated block of size 24 bytes:
	 *    Allocated Block Header:
	 *      If the previous block is free      p-bit=0 size_status would be 25
	 *      If the previous block is allocated p-bit=1 size_status would be 27
	 * 
	 * 2. Free block of size 24 bytes:
	 *    Free Block Header:
	 *      If the previous block is free      p-bit=0 size_status would be 24
	 *      If the previous block is allocated p-bit=1 size_status would be 26
	 *    Free Block Footer:
	 *      size_status should be 24
	 */
} blockHeader;         

/* Global variable - DO NOT CHANGE. It should always point to the first block,
 * i.e., the block at the lowest address.
 */
blockHeader *heap_start = NULL;     

/* Size of heap allocation padded to round to nearest page size.
*/
int alloc_size;

/*
 * Additional global variables may be added as needed below
 */


/* 
 * Function for allocating 'size' bytes of heap memory.
 * Argument size: requested size for the payload
 * Returns address of allocated block (payload) on success.
 * Returns NULL on failure.
 *
 * This function must:
 * - Check size - Return NULL if not positive or if larger than heap space.
 * - Determine block size rounding up to a multiple of 8 
 *   and possibly adding padding as a result.
 *
 * - Use BEST-FIT PLACEMENT POLICY to chose a free block
 *
 * - If the BEST-FIT block that is found is exact size match
 *   - 1. Update all heap blocks as needed for any affected blocks
 *   - 2. Return the address of the allocated block payload
 *
 * - If the BEST-FIT block that is found is large enough to split 
 *   - 1. SPLIT the free block into two valid heap blocks:
 *         1. an allocated block
 *         2. a free block
 *         NOTE: both blocks must meet heap block requirements 
 *       - Update all heap block header(s) and footer(s) 
 *              as needed for any affected blocks.
 *   - 2. Return the address of the allocated block payload
 *
 * - If a BEST-FIT block found is NOT found, return NULL
 *   Return NULL unable to find and allocate block for desired size
 *
 * Note: payload address that is returned is NOT the address of the
 *       block header.  It is the address of the start of the 
 *       available memory for the requesterr.
 *
 * Tips: Be careful with pointer arithmetic and scale factors.
 */
void* balloc(int size) {     
	//Check size
	if ((size <= 0) || (size > alloc_size)){
		return NULL;
	}

	//Calculate block size needed
	int blockSizeNeeded = size + sizeof(blockHeader);//For block header
	if (blockSizeNeeded % 8 != 0) {
		blockSizeNeeded += 8-(blockSizeNeeded % 8);
	}

	//Initialize the smallest fitting header to be heap_start
	blockHeader* smallestFittingHeader = heap_start;
	int smallestFittingSize = alloc_size;
	int sfPrevAlloc = 1;

	//currentHeader of loop
	blockHeader* currentHeader = heap_start;




	//End mark has size_status of 1
	while(currentHeader->size_status != 1) {
		int prevAlloc = 0;
		int currAlloc = 0;
		int currBlockSize = 0;

		//Parse the header
		if (currentHeader->size_status % 4 == 0) {
			prevAlloc = 0;
			currAlloc = 0;
			currBlockSize = currentHeader->size_status;
		}
		else if (currentHeader->size_status % 4 == 1) {
			prevAlloc = 0;
			currAlloc = 1;
			currBlockSize = currentHeader->size_status - 1;

		}
		else if (currentHeader->size_status % 4 == 2) {
			prevAlloc = 1;
			currAlloc = 0;
			currBlockSize = currentHeader->size_status - 2;
		}
		else if (currentHeader->size_status % 4 == 3) {
			prevAlloc = 1;
			currAlloc = 1;
			currBlockSize = currentHeader->size_status - 3;
		}
		//If block is not allocated
		if (currAlloc == 0){
			//perfect size
			if (currBlockSize == blockSizeNeeded) {
				//might be wrong if it creates copy insted of direct reference, but this points to next header
				blockHeader *nextHeader = currentHeader + currBlockSize/4;



				//update next block previous address by adding 2
				if (nextHeader->size_status != 1) {
					nextHeader->size_status = nextHeader->size_status +  2;
				}

				//mark current block as allocated
				if (prevAlloc == 0) {
					currentHeader->size_status = blockSizeNeeded + 1;
				}
				else {
					currentHeader->size_status = blockSizeNeeded + 3;
				}
				//return currentHeader address  + 4 ( 4bytes);
				return (void*)currentHeader + sizeof(blockHeader);

			}//check if it is smaller block that fits
			else {
				//saves smallest block size
				if (currBlockSize >= blockSizeNeeded + 8){
					if (currBlockSize < smallestFittingSize) {
						smallestFittingHeader = currentHeader;
						smallestFittingSize = currBlockSize;
						sfPrevAlloc = prevAlloc;

					}
				}

				currentHeader = currentHeader + currBlockSize/4;
			}
		} //move on to next block
		else {
			currentHeader = currentHeader + currBlockSize/4;
		}
	}


	//If the smallest block is still the first block, but other blocks have been allocated.	
	if (smallestFittingSize == alloc_size){ 
		if ((heap_start->size_status  - heap_start->size_status%4) != alloc_size){
			return NULL;
		}
	}






	//Allocate to smallest fitting header
	if (smallestFittingSize >= blockSizeNeeded + 8){
		if (smallestFittingHeader->size_status % 4 == 0 || smallestFittingHeader->size_status % 4 == 2) {//Basically check that the starting heap is not fully allocated
			blockHeader *nextHeaderBlock = (blockHeader*) ((void*)smallestFittingHeader + blockSizeNeeded);
			//calculate currBlock size again (smallestFittingSize
			//update next block previous address by adding 2
			if (nextHeaderBlock->size_status != 1) {
				nextHeaderBlock->size_status = (smallestFittingSize-blockSizeNeeded) + 2;
			}
			//update next block by adjusting the footer
			blockHeader *nextFooter =  (nextHeaderBlock + (smallestFittingSize-blockSizeNeeded)/4 - 1);
			nextFooter->size_status = (smallestFittingSize-blockSizeNeeded);


			//mark current block as allocated
			if (sfPrevAlloc == 0) {
				smallestFittingHeader->size_status = blockSizeNeeded + 1;
			}
			else {
				smallestFittingHeader->size_status = blockSizeNeeded + 3;
			}       

			//return currentHeader address  + 4 (4 bytes);
			return (void*)smallestFittingHeader + sizeof(blockHeader);
		}	
	}

	//if it gets to this point it means there is no valid block to allocate
	return NULL;
} 

/* 
 * Function for freeing up a previously allocated block.
 * Argument ptr: address of the block to be freed up.
 * This function should:
 * - Return -1 if ptr is NULL.
 * - Return -1 if ptr is not a multiple of 8.
 * - Return -1 if ptr is outside of the heap space.
 * - Return -1 if ptr block is already freed.
 * - Update header(s) and footer as needed.
 */                    
int bfree(void *ptr) {    
	//Check for valid input
	if (ptr == NULL) { 
		return -1;
	}
	if ((unsigned int) ptr % 8 != 0) {
		return -1;
	}
	if ((unsigned int) ptr % 8 > alloc_size) {
		return -1;
	}

	//Assign ptr header
	blockHeader*  ptrHeader = (blockHeader*) (ptr -4);

	//Check if free or alloc
	if (ptrHeader->size_status % 4 == 0 || ptrHeader->size_status % 4 == 2) {
		return -1;
	} else {
		//set ptr size
		int ptrSize = 0;
		ptrSize = ptrHeader->size_status - (ptrHeader->size_status % 4);	


		//Set header of currentBlock as free
		ptrHeader->size_status = ptrHeader->size_status - 1;
		//Create footer
		blockHeader *ptrFooter =  (ptrHeader + (ptrSize/4) - 1);
		ptrFooter->size_status = ptrSize;

		//Change the prev value of next block
		blockHeader *nextHeader = ptrHeader + ptrSize/4;
		if (nextHeader->size_status != 1) {
			//update next block previous address by subtracting 2
			nextHeader->size_status = nextHeader->size_status -  2;
		}

		return 0;
	}
	return -1;
} 

/*
 * Function for traversing heap block list and coalescing all adjacent 
 * free blocks.
 *
 * This function is used for delayed coalescing.
 * Updated header size_status and footer size_status as needed.
 */
int coalesce() {

	//currentHeader of loop
	blockHeader* currentHeader = heap_start;

	//default to no allocation occured
	int didCoalesce = 0;


	//End mark has size_status of 1, stop when nextBlock is 1
	while(currentHeader->size_status != 1) {
		int prevAlloc = 0;
		int currAlloc = 0;
		int currBlockSize = 0;



		//Parse the header
		if (currentHeader->size_status % 4 == 0) {
			prevAlloc = 0;
			currAlloc = 0;
			currBlockSize = currentHeader->size_status;
		}
		else if (currentHeader->size_status % 4 == 1) {
			prevAlloc = 0;
			currAlloc = 1;
			currBlockSize = currentHeader->size_status - 1;

		}
		else if (currentHeader->size_status % 4 == 2) {
			prevAlloc = 1;
			currAlloc = 0;
			currBlockSize = currentHeader->size_status - 2;
		}
		else if (currentHeader->size_status % 4 == 3) {
			prevAlloc = 1;
			currAlloc = 1;
			currBlockSize = currentHeader->size_status - 3;
		}

		//If the current block is free try to coalesce
		if (currAlloc == 0) {

			//Initialize first next block
			blockHeader *nextHeader = currentHeader + currBlockSize/4;
			//Parse the nextHeader
			int nextAlloc = 0;
			int nextBlockSize = 0;
			if (nextHeader->size_status % 4 == 0) {
				nextAlloc = 0;
				nextBlockSize = nextHeader->size_status;
			}
			else if (nextHeader->size_status % 4 == 1) {
				nextAlloc = 1;
				nextBlockSize = nextHeader->size_status - 1;

			}
			else if (nextHeader->size_status % 4 == 2) {
				nextAlloc = 0;
				nextBlockSize = nextHeader->size_status - 2;

			}
			else if (nextHeader->size_status % 4 == 3) {
				nextAlloc = 1;
				nextBlockSize = nextHeader->size_status - 3;
			}


			//check to see if nextheader is end mark



			//Initialize and locate the current footer
			blockHeader *currFooter = currentHeader + currBlockSize/4 - 1;
			while(nextAlloc == 0) {//While condition checks if the nextHeader is allocated
					       //Add the sizes
				currBlockSize += nextBlockSize;
				didCoalesce = 1;

				//Change next header and parse again
				nextHeader = nextHeader + nextBlockSize/4;
				if (nextHeader->size_status % 4 == 0) {
					nextAlloc = 0;
					nextBlockSize = nextHeader->size_status;
				}
				else if (nextHeader->size_status % 4 == 1) {
					nextAlloc = 1;
					nextBlockSize = nextHeader->size_status - 1;

				}
				else if (nextHeader->size_status % 4 == 2) {
					nextAlloc = 0;           
					nextBlockSize = nextHeader->size_status - 2;

				}
				else if (nextHeader->size_status % 4 == 3) {
					nextAlloc = 1;
					nextBlockSize = nextHeader->size_status - 3;
				}


			}
			//Loop through next blocks, if next block is allocated stop looping


			//Change header to the curr block size which was just changed then add the prevAlloc
			if (prevAlloc == 1) {
				currentHeader->size_status =  currBlockSize + 2;
			} else {
				currentHeader->size_status = currBlockSize;
			}

			//Change footer as well 
			currFooter = currentHeader + currBlockSize/4 - 1;
			currFooter->size_status = currBlockSize;


			//if next block status = 1 return didAllocate;
			if (nextHeader->size_status == 1) {
				return didCoalesce;	
			}
		}

		//move on to the next block
		currentHeader = currentHeader + currBlockSize/4;
	}


	return didCoalesce;
}


/* 
 * Function used to initialize the memory allocator.
 * Intended to be called ONLY once by a program.
 * Argument sizeOfRegion: the size of the heap space to be allocated.
 * Returns 0 on success.
 * Returns -1 on failure.
 */                    
int init_heap(int sizeOfRegion) {    

	static int allocated_once = 0; //prevent multiple myInit calls

	int pagesize;   // page size
	int padsize;    // size of padding when heap size not a multiple of page size
	void* mmap_ptr; // pointer to memory mapped area
	int fd;

	blockHeader* end_mark;

	if (0 != allocated_once) {
		fprintf(stderr, 
				"Error:mem.c: InitHeap has allocated space during a previous call\n");
		return -1;
	}

	if (sizeOfRegion <= 0) {
		fprintf(stderr, "Error:mem.c: Requested block size is not positive\n");
		return -1;
	}

	// Get the pagesize
	pagesize = getpagesize();

	// Calculate padsize as the padding required to round up sizeOfRegion 
	// to a multiple of pagesize
	padsize = sizeOfRegion % pagesize;
	padsize = (pagesize - padsize) % pagesize;

	alloc_size = sizeOfRegion + padsize;

	// Using mmap to allocate memory
	fd = open("/dev/zero", O_RDWR);
	if (-1 == fd) {
		fprintf(stderr, "Error:mem.c: Cannot open /dev/zero\n");
		return -1;
	}
	mmap_ptr = mmap(NULL, alloc_size, PROT_READ | PROT_WRITE, MAP_PRIVATE, fd, 0);
	if (MAP_FAILED == mmap_ptr) {
		fprintf(stderr, "Error:mem.c: mmap cannot allocate space\n");
		allocated_once = 0;
		return -1;
	}

	allocated_once = 1;

	// for double word alignment and end mark
	alloc_size -= 8;

	// Initially there is only one big free block in the heap.
	// Skip first 4 bytes for double word alignment requirement.
	heap_start = (blockHeader*) mmap_ptr + 1;

	// Set the end mark
	end_mark = (blockHeader*)((void*)heap_start + alloc_size);
	end_mark->size_status = 1;

	// Set size in header
	heap_start->size_status = alloc_size;

	// Set p-bit as allocated in header
	// note a-bit left at 0 for free
	heap_start->size_status += 2;

	// Set the footer
	blockHeader *footer = (blockHeader*) ((void*)heap_start + alloc_size - 4);
	footer->size_status = alloc_size;

	return 0;
} 

/* 
 * Function to be used for DEBUGGING to help you visualize your heap structure.
 * Prints out a list of all the blocks including this information:
 * No.      : serial number of the block 
 * Status   : free/used (allocated)
 * Prev     : status of previous block free/used (allocated)
 * t_Begin  : address of the first byte in the block (where the header starts) 
 * t_End    : address of the last byte in the block 
 * t_Size   : size of the block as stored in the block header
 */                     
void disp_heap() {     

	int counter;
	char status[6];
	char p_status[6];
	char *t_begin = NULL;
	char *t_end   = NULL;
	int t_size;

	blockHeader *current = heap_start;
	counter = 1;

	int used_size = 0;
	int free_size = 0;
	int is_used   = -1;

	fprintf(stdout, 
			"*********************************** Block List **********************************\n");
	fprintf(stdout, "No.\tStatus\tPrev\tt_Begin\t\tt_End\t\tt_Size\n");
	fprintf(stdout, 
			"---------------------------------------------------------------------------------\n");

	while (current->size_status != 1) {
		t_begin = (char*)current;
		t_size = current->size_status;

		if (t_size & 1) {
			// LSB = 1 => used block
			strcpy(status, "alloc");
			is_used = 1;
			t_size = t_size - 1;
		} else {
			strcpy(status, "FREE ");
			is_used = 0;
		}

		if (t_size & 2) {
			strcpy(p_status, "alloc");
			t_size = t_size - 2;
		} else {
			strcpy(p_status, "FREE ");
		}

		if (is_used) 
			used_size += t_size;
		else 
			free_size += t_size;

		t_end = t_begin + t_size - 1;

		fprintf(stdout, "%d\t%s\t%s\t0x%08lx\t0x%08lx\t%4i\n", counter, status, 
				p_status, (unsigned long int)t_begin, (unsigned long int)t_end, t_size);

		current = (blockHeader*)((char*)current + t_size);
		counter = counter + 1;
	}

	fprintf(stdout, 
			"---------------------------------------------------------------------------------\n");
	fprintf(stdout, 
			"*********************************************************************************\n");
	fprintf(stdout, "Total used size = %4d\n", used_size);
	fprintf(stdout, "Total free size = %4d\n", free_size);
	fprintf(stdout, "Total size      = %4d\n", used_size + free_size);
	fprintf(stdout, 
			"*********************************************************************************\n");
	fflush(stdout);

	return;  
} 


// end of myHeap.c (Spring 2022)                                         


