#include "types.h"
#include "stat.h"
#include "user.h"
int main(void) 
{
  printf(1, "The process ID is: %d\n", (nice(5)));
  if (fork() == 0) {
    printf(1, "Child");
    sleep(101);
  } else {
    printf(1, "Parent");
  }


  exit();
}
