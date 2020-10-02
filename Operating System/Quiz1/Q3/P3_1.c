#include <stdio.h>
int main()
{	char x[64];
	
	// char *ptr;
	long int *ptr=(long int *)(&(x[0]));
	for (int i = 0; i < 8; i++)
	{		
			*ptr=9223372036854775807;
			ptr++;  
	}
	ptr-=8;  //move pointer to start point of char array
	for (int i = 0; i < 8; i++)
	{
		printf("%ld ",*ptr);
		ptr++;
	}
	printf("\n");


	return 0;
}