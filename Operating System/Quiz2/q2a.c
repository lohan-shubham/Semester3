// C Program for Message Queue (Writer Process) 
#include <stdio.h> 
#include <sys/ipc.h> 
#include <sys/msg.h> 
#include <string.h>

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
    message.mesg_type = 1; 

    FILE *fp;
    fp=fopen("para1.txt","r");
    char display[100];
    while(fgets(display,100,fp)!=NULL){
        // strcat(message.mesg_text,display);

        char *split = strtok(display," ");
        while(split!=NULL){
            strcpy(message.mesg_text,split);
       
            msgsnd(msgid, &message, sizeof(message), 0); 

            split = strtok(NULL," ");
        }


    }
    fclose(fp);
    // msgsnd to send message 
    // msgsnd(msgid, &message, sizeof(message), 0); 

    // display the message 

    return 0; 
} 
