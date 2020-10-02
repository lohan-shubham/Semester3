#include <stdio.h>
int main()
{	char x[64];
	
	// char *ptr;
	int *ptr=(int *)(&(x[0]));
	for (int i = 0; i < 16; i++)
	{		
			*ptr=2147483647;
			ptr++;
	}
	ptr-=16;
	for (int i = 0; i < 16; i++)
	{
		printf("%d ",*ptr);
		ptr++;
	}
	printf("\n");


	return 0;
}