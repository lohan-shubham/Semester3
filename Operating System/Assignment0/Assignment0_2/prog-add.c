#include<stdio.h>
#include<inttypes.h>
int add(int ,int );
int main()
{
	int a,b;
	printf("Enter two numbers to be added: ");
	scanf("%d %d",&a,&b);
	int c=add(a,b);
	printf("Sum is: %d \n",c);
	return 0;
}