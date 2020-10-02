#include<stdio.h>
#include<inttypes.h>
// int long_add(int ,int ,int,int);
int extended_add(int,int,int,int);
int main()
{
	int a,b,c,d;
	printf("Enter numbers to be added: ");
	scanf("%d %d %d %d",&a,&b,&c,&d);
	// int ans=long_add(a,b,c,d);
	int ans=extended_add(a,b,c,d);
	printf("Sum is: %d \n",ans);
	return 0;
}