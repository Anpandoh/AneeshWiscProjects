#include "types.h"
#include "stat.h"
#include "user.h"
int main(void) 
{
  char buffer[256];
  getlastcat(buffer);
  printf(1, "XV6_TEST_OUTPUT Last catted filename: %s\n", buffer);
  exit();
}