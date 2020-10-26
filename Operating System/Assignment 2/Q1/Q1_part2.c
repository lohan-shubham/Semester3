/* Name: Shubham Lohan
   Roll_Number: 2019275 */
#include <sys/types.h> 
#include <sys/wait.h>
#include<stdio.h>
#include <pthread.h> 
int global_number=0;
void* ChildProcess(){
   printf("\n\nChild Process\n\n");
   while(global_number>-90){
      --global_number;
      printf("%d ",global_number);
   }
}
void* ParentProcess(){
   printf("\n\nParent Process\n\n");
   while(global_number<100){
      ++global_number;
      printf("%d ",global_number);
   }
}
int main() {
   pthread_t ptid;
   int status;
   pthread_create(&ptid, NULL, &ChildProcess, NULL); 
   pthread_join(ptid,NULL);
   pthread_create(&ptid,NULL,&ParentProcess,NULL);
   pthread_exit(NULL);
    return 0;
}
