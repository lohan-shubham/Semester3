#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <netinet/in.h> 
int main() { 
	int sockfd; 
	char buffer[1000];

	struct sockaddr_in	 servaddr; 

	if ( (sockfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0 ) { 
		perror("socket creation failed"); 
		exit(EXIT_FAILURE); 
	} 

	memset(&servaddr, 0, sizeof(servaddr)); 
	
	servaddr.sin_family = AF_INET; 
	servaddr.sin_port = htons(2308); 
	servaddr.sin_addr.s_addr = INADDR_ANY; 
	
	int n, len; 
	
	FILE *fp;
    fp=fopen("para2.txt","r");
    char display[100];
    while(fgets(display,100,fp)!=NULL){
      

        char *split = strtok(display," ");
        while(split!=NULL){
       
            sendto(sockfd, (const char *)split, strlen(split), 
		MSG_CONFIRM, (const struct sockaddr *) &servaddr, 
			sizeof(servaddr)); 

            split = strtok(NULL," ");
        }


    }
    sendto(sockfd, (const char *)"\n\n", strlen("\n\n"), 
			MSG_CONFIRM, (const struct sockaddr *) &servaddr, 
			sizeof(servaddr));
    fclose(fp);

	close(sockfd); 
	return 0; 
} 
