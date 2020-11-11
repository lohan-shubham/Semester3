/* Name: Shubham Lohan
   Roll_Number: 2019275 */
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
int global_number = 10;
void *ChildProcess()
{
   printf("\n\nChild Process\n\n");
   while (global_number > -90)
   {
      --global_number;
      printf("%d ", global_number);
   }
   exit(EXIT_SUCCESS);
}
void *ParentProcess()
{
   printf("\n\nParent Process\n\n");
   while (global_number < 100)
   {
      ++global_number;
      printf("%d ", global_number);
   }
   exit(EXIT_SUCCESS);
}
int main()
{
   pid_t pid;
   int status;
   pid = fork();
   if (pid == 0)
   {
      ChildProcess();
   }
   else if (pid > 0)
   {
      waitpid(pid, &status, 0);
      ParentProcess();
   }
   else
   {
      printf("Process cant't be created");
   }
   return 0;
}