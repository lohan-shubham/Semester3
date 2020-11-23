
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

	struct sockaddr_in servaddr, cliaddr; 
	

	if ( (sockfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0 ) { 
		perror("socket creation failed"); 
		exit(EXIT_FAILURE); 
	} 
	
	memset(&servaddr, 0, sizeof(servaddr)); 
	memset(&cliaddr, 0, sizeof(cliaddr)); 
	

	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = INADDR_ANY; 
	servaddr.sin_port = htons(2308); 
	

	if ( bind(sockfd, (const struct sockaddr *)&servaddr, 
			sizeof(servaddr)) < 0 ) 
	{ 
		perror("bind failed"); 
		exit(EXIT_FAILURE); 
	} 
	
	int len, n; 

	len = sizeof(cliaddr);

	do{ 
		n = recvfrom(sockfd, (char *)buffer, 1000, 
				MSG_WAITALL, ( struct sockaddr *) &cliaddr, 
				&len);
		if(n==-1)
			break;
		buffer[n] = '\0'; 
		printf("%s ", buffer); 
	}while(strcmp(buffer,"\n\n")!=0);
	
	return 0; 
} 
