#include "types.h"
#include "stat.h"
#include "user.h"

int main(void)
{
    char message[256];
    int retVal = getlastcat(message);
    printf(1, "XV6_TEST_OUTPUT: %d\n", retVal);
    exit();
}