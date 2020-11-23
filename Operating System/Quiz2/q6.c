#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>
#include<semaphore.h>
#include<unistd.h>

sem_t mutex;
sem_t S[5];

void * philosopher(void * num){
	int phil=*(int *)num;

	sem_wait(&mutex);
	sem_wait(&S[phil]);
	sem_wait(&S[(phil+1)%5]);
	printf("P%d recieves F%d,F%d\n",phil,phil, (phil+1)%5);

	sem_post(&S[(phil+1)%5]);
	sem_post(&S[phil]);
	sem_post(&mutex);
}

int main(){

	pthread_t ptid[5];
	int phil[5]={0,1,2,3,4};
	sem_init(&mutex,0,1);

	for(int i=0;i<5;i++){
		sem_init(&S[i],0,1);
	}
	for(int i=0;i<5;i++){
		pthread_create(&ptid[i],NULL,philosopher,(void*)&phil[i]);
	}
		for(int i=0;i<5;i++){
					pthread_join(ptid[i],NULL);
		}
	}