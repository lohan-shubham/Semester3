/* Name: Shubham Lohan
   Roll_Number: 2019275 */

#include <stdio.h>
#include <linux/kernel.h>
#include <sys/syscall.h>
#include <unistd.h>
#include <sys/time.h>
#include <sys/wait.h>

int main()
{
	long pid,tem,i=0;
	int status;
	pid=fork();
	if(pid>0)
	{

		printf("PID: %d\n",getpid());
		syscall(441,getpid(),10);
		struct timeval t1,t2;
		gettimeofday(&t1,NULL);
		for(i=0;i<10000000;i++);
		gettimeofday(&t2,NULL);		
		printf("\n%lf  this is Parent process\n",(double)(t2.tv_usec-t1.tv_usec)/1000 + (double)(t2.tv_sec-t1.tv_sec)*1000);
		waitpid(pid,&status,0);
	}
	else if(pid==0)
	{
		// syscall(441,getpid(),10);
		struct timeval t1,t2;
		gettimeofday(&t1,NULL);
		for(i=0;i<10000000;i++);
		gettimeofday(&t2,NULL);
		printf("\n%lf  this is Child process\n",(double)(t2.tv_usec-t1.tv_usec)/1000 + (double)(t2.tv_sec-t1.tv_sec)*1000);
		

	}
	else{

		printf("Can't create child process\n");
		return -1;
	}
	return 0;

}