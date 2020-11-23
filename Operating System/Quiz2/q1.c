
#include<stdio.h> 
#include<stdlib.h> 
#include<unistd.h> 
#include<sys/types.h> 
#include<string.h> 
#include<ctype.h>
#include<sys/wait.h> 

int main() 
{ 
	int fd1[2];
	int fd2[2];

	char *input_str=(char *)malloc(sizeof(char)*500);
	pid_t pid; 
	int status;

	int flag;
	flag=pipe(fd1);
	flag+= pipe(fd2);

	if (flag<0) 
	{ 
		fprintf(stderr, "Pipe Failed" ); 
		return -1; 
	} 

	scanf("%[^\n]%*c", input_str); 

	pid = fork(); 

	if (pid < 0) 
	{ 
		fprintf(stderr, "fork Failed" ); 
		return -1; 
	} 


	else if (pid > 0) 
	{ 
		close(fd1[0]);
		write(fd1[1], input_str, strlen(input_str)+1); 
		close(fd1[1]); 
		waitpid(pid,&status,0);
		close(fd2[1]);
		read(fd2[0], input_str, 100); 
		puts(input_str);
		close(fd2[0]); 
	} 
	else
	{ 
		close(fd1[1]);
		read(fd1[0], input_str, 100); 
		for(int i=0;i<strlen(input_str) && input_str[i]!='\0';i++){
			if(input_str[i]==92){

				input_str[i]=32; // ASCII value for space
				i++;
				if(input_str[i]=='t') //tab
					input_str[i]=9;  
				if(input_str[i]=='n')  //newline
					input_str[i]=10;
				if(input_str[i]=='r')  //cariage return
					input_str[i]=13;
				if(input_str[i]=='v')  //vertical tab
					input_str[i]=11;
				if(input_str[i]=='a')  //bell
					input_str[i]=7;
				if(input_str[i]=='b')	//backspace
					input_str[i]=8;		
				if(input_str[i]=='f')  //form feed
					input_str[i]=12;
				if(input_str[i]==92)  //ASCII value of '/'
					input_str[i]=92;
				continue;

			}
			input_str[i]=toupper(input_str[i]);

		}
		close(fd1[0]); 
		close(fd2[0]); 
		write(fd2[1], input_str, strlen(input_str)+1); 
		close(fd2[1]); 

		exit(0); 
	} 
} 
