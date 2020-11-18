// C program to demonstrate use of fork() and pipe() 
#include<stdio.h> 
#include<stdlib.h> 
#include<unistd.h> 
#include<sys/types.h> 
#include<string.h> 
#include<ctype.h>
#include<sys/wait.h> 

int main() 
{ 
	// We use two pipes 
	// First pipe to send input string from parent 
	// Second pipe to send concatenated string from child 

	int fd1[2]; // Used to store two ends of first pipe 
	int fd2[2]; // Used to store two ends of second pipe 
	// char input_str[100]; 
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

	// Parent process 
	else if (pid > 0) 
	{ 
	

		close(fd1[0]); // Close reading end of first pipe 

		// Write input string and close writing end of first 
		// pipe. 
		write(fd1[1], input_str, strlen(input_str)+1); 
		close(fd1[1]); 

		// Wait for child to send a string 
		waitpid(pid,&status,0); 

		close(fd2[1]); // Close writing end of second pipe 

		// Read string from child, print it and close 
		// reading end. 
		read(fd2[0], input_str, 100); 
		// printf("%s", input_str); 
		puts(input_str);
		close(fd2[0]); 
	} 

	// child process 
	else
	{ 
		close(fd1[1]); // Close writing end of first pipe 

		// Read a string using first pipe 
		read(fd1[0], input_str, 100); 

		// Concatenate a fixed string with it 
	

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

		// Close both reading ends 
		close(fd1[0]); 
		close(fd2[0]); 

		// Write concatenated string and close writing end 
		write(fd2[1], input_str, strlen(input_str)+1); 
		close(fd2[1]); 

		exit(0); 
	} 
} 
