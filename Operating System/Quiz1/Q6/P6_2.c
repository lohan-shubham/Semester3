#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE * fPtr;
    fPtr = fopen("name.txt", "w");
    fputs("Shubham", fPtr);
    fclose(fPtr);
    return 0;
}