/*Name: Shubham Lohan
Roll No: 2019275*/

#include <pthread.h>
#include <unistd.h>
#include <stdio.h>

int no_of_philosophers;
#define TAKE_FORK 0
#define PUT_FORK 1
#define MAX 1000
int state[MAX];
int phil_arr[MAX];

struct my_samaphore
{
    pthread_cond_t cond;
    pthread_mutex_t mtx;
    volatile unsigned counter;
};

struct my_samaphore mutex;
struct my_samaphore S[MAX];

int left_phil(int philNo)
{
    return (philNo + no_of_philosophers - 1) % no_of_philosophers;
}
int right_phil(int philNo)
{
    return (philNo + 1) % no_of_philosophers;
}
void init(struct my_samaphore *ps, int counter)
{
    pthread_mutex_init(&ps->mtx, NULL);
    pthread_cond_init(&ps->cond, NULL);
    ps->counter = counter;
}
int wait(struct my_samaphore *ps)
{
    pthread_mutex_lock(&ps->mtx);
    --(ps->counter);
    pthread_mutex_unlock(&ps->mtx);
    if (ps->counter <= 0)
        return -1;
    return 0;
}

void signal(struct my_samaphore *ps)
{
    pthread_mutex_lock(&ps->mtx);
    ++(ps->counter);
    pthread_mutex_unlock(&ps->mtx);
    pthread_cond_signal(&ps->cond);
}

void signal2(struct my_samaphore *ps, int printvalue)
{
    pthread_mutex_lock(&ps->mtx);
    printf("value of signal: %d", printvalue);
    ++(ps->counter);
    pthread_mutex_unlock(&ps->mtx);
    pthread_cond_signal(&ps->cond);
}

void check(int philNo)
{
    if (state[philNo] == 1 && state[left_phil(philNo)] != 0 && state[right_phil(philNo)] != 0)
    {
        state[philNo] = 0;
        printf("Philosopher %d eats using forks %d and %d\n", philNo + 1, left_phil(philNo) + 1, right_phil(philNo) + 1);
        signal(&S[philNo]);
    }
}
void FORK(int philNo, int action)
{
    while (wait(&mutex) == -1)
    {
        // printf("bowl waiting\n");
    }

    if (action == TAKE_FORK)
    {
        state[philNo] = 1;
        check(philNo);
        signal(&mutex);
        while (wait(&S[philNo]) == -1)
        {
            // printf("waiting\n");
        }
        return;
    }
    if (action == PUT_FORK)
    {
        state[philNo] = 2;
        check(left_phil(philNo));
        check(right_phil(philNo));
        signal(&mutex);
        return;
    }
}

void *philospher(void *num)
{
    while (1)
    {
        int philNo = *(int *)num;
        sleep(1);
        FORK(philNo, TAKE_FORK);
        sleep(1);
        FORK(philNo, PUT_FORK);
        // break;
    }
}

int main()
{
    printf("Enter no of philospher: ");
    scanf("%d", &no_of_philosophers);

    if (no_of_philosophers < 1 || no_of_philosophers > MAX)
    {
        printf("Enter correct No of Philosopher\n");
        return 0;
    }

    pthread_t thread_id[no_of_philosophers];
    for (int i = 0; i < no_of_philosophers; i++)
        phil_arr[i] = i;

    init(&mutex, 1);
    for (int i = 0; i < no_of_philosophers; i++)

        init(&S[i], 0);

    for (int i = 0; i < no_of_philosophers; i++)
        pthread_create(&thread_id[i], NULL, philospher, &phil_arr[i]);

    for (int i = 0; i < no_of_philosophers; i++)
        pthread_join(thread_id[i], NULL);
    return 0;
}
