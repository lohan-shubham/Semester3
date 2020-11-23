#include <stdio.h> 
#include <sys/ipc.h> 
#include <sys/msg.h> 
#include <string.h>
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
    message.mesg_type = 1; 

    FILE *fp;
    fp=fopen("para2.txt","r");
    if(fp==NULL){
		printf("File cant be opened\n");
		return -1;
	}
    char display[100];
    while(fgets(display,100,fp)!=NULL){


        char *split = strtok(display," ");
        while(split!=NULL){
            strcpy(message.mesg_text,split);
     
            msgsnd(msgid, &message, sizeof(message), 0); 

            split = strtok(NULL," ");
        }
    }
    fclose(fp);


    return 0; 
} 
