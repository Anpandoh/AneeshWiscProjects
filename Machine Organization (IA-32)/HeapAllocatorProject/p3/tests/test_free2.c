// many odd sized allocations and interspersed frees
#include <assert.h>
#include <stdlib.h>
#include "p3Heap.h"
#include <stdio.h>
int main() {
   assert(init_heap(4096) == 0);
   void * ptr[9];
   ptr[0] = balloc(1);
   ptr[1] = (balloc(5));
   ptr[2] = (balloc(8));
   ptr[3] = (balloc(14));
   disp_heap();
   assert(ptr[0] != NULL);
   assert(ptr[1] != NULL);
   assert(ptr[2] != NULL);
   assert(ptr[3] != NULL);
   
   assert(bfree(ptr[1]) == 0);
   assert(bfree(ptr[0]) == 0);
   assert(bfree(ptr[3]) == 0);
   
   ptr[4] = (balloc(1));
   ptr[5] = (balloc(4));
   assert(ptr[4] != NULL);
   assert(ptr[5] != NULL);
   disp_heap();

   printf("FAILED?");
   assert(bfree(ptr[5]) == 0);
   
   ptr[6] = (balloc(9));
   ptr[7] = (balloc(33));
   assert(ptr[6] != NULL);
   assert(ptr[7] != NULL);
   
   assert(bfree(ptr[4]) == 0);

   ptr[8] = (balloc(55));
   assert(ptr[8] != NULL);

   assert(bfree(ptr[2]) == 0);
   assert(bfree(ptr[7]) == 0);
   assert(bfree(ptr[8]) == 0);
   assert(bfree(ptr[6]) == 0);

   exit(0);
}
