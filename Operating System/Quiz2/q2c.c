    
#include <stdio.h> 
#include <sys/ipc.h> 
#include <sys/msg.h> 
#include <string.h>
#include <stdlib.h>
#include <errno.h>

struct mesg_buffer { 
    long mesg_type; 
    char mesg_text[100]; 
} message; 

int main() 
{ 
    key_t key; 
    int msgid; 
    key = ftok("mycode", 65); 
    msgid = msgget(key, 0666 | IPC_CREAT); 
    
    while(1){

        if (msgrcv(msgid, &message, sizeof(message), 1, IPC_NOWAIT) == -1)
        {
            break;
        }
            printf("%s ",message.mesg_text); 
    }
    msgctl(msgid, IPC_RMID, NULL); 

    return 0; 
} 
