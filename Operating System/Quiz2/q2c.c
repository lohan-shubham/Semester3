    // C Program for Message Queue (Reader Process) 
#include <stdio.h> 
#include <sys/ipc.h> 
#include <sys/msg.h> 
#include <string.h>
#include <stdlib.h>
#include <errno.h>
// structure for message queue 
struct mesg_buffer { 
    long mesg_type; 
    char mesg_text[100]; 
} message; 

int main() 
{ 
    key_t key; 
    int msgid; 

    // ftok to generate unique key 
    key = ftok("mycode", 65); 

    // msgget creates a message queue 
    // and returns identifier 
    msgid = msgget(key, 0666 | IPC_CREAT); 

    // msgrcv to receive message 
    while(1){

        if (msgrcv(msgid, &message, sizeof(message), 1, IPC_NOWAIT) == -1)
        {
            break;
        }
        // msgrcv(msgid, &message, sizeof(message), 1, 0);
            // display the message 
    
            printf("%s",message.mesg_text); 
    }
    // to destroy the message queue 
    msgctl(msgid, IPC_RMID, NULL); 

    return 0; 
} 
