/* Name: Shubham Lohan
   Roll_Number: 2019275 */
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <pthread.h>
int global_number = 10;
void *ChildProcess(void* arg)
{
   printf("\n\nChild Process\n\n");
   while (global_number > -90)
   {
      --global_number;
      printf("%d ", global_number);
   }
   pthread_exit(NULL); 
}
void *ParentProcess(void* arg)
{
   printf("\n\nParent Process\n\n");
   while (global_number < 100)
   {
      ++global_number;
      printf("%d ", global_number);
   }
   pthread_exit(NULL); 
}
int main()
{
   pthread_t ptid;
   int status;
   if (pthread_create(&ptid, NULL, &ChildProcess, NULL))
   {
      perror("Child Thread is not created\n");
      return -1;
   }
   if (pthread_join(ptid, NULL))
   {
      perror("Thread cant not waited\n");
      return -1;
   }
   if (pthread_create(&ptid, NULL, &ParentProcess, NULL))
   {
      perror("Thread is not created\n");
      return -1;
   }
   pthread_exit(NULL);
   return 0;
}
