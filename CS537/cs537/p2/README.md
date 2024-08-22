name: Aneesh Pandoh

cslogin: pandoh

netID: pandoh

email: pandoh@wisc.edu

status: complete

Files I changed:
syscall.c,
user.h,
syscall.h,
- For adding syscall


Most of logic is implemented in:

sysfile.c - Modified sysopen and sysread to store cat values along with creating syscall func


getlastcat - userfacing program that calls system call.


I used chatgpt simply to figure out how to check for NULL when I cant use stddef, here is link to conversation:
https://chat.openai.com/share/ab8e8c65-bccb-4c85-9649-45aed0655fdb
