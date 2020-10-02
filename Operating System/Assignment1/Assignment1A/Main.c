#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
void process(char SECTION[])
{
	FILE *file;
	char display[100];
	file = fopen("OS.csv", "r");
	if(file==NULL){
		write(1,"File cant be opened\n",sizeof("File cant be opened\n")-1);
		return;
	}
	write(1, "Student ID\tSection no\tAverage Marks\n", sizeof("Student ID\tSection no\tAverage Marks\n") - 1);
	
	while (!feof(file))
	{
		fgets(display, 100, file);
		char *ptr = strtok(display, ",");
		int counter = 0;
		while (ptr != NULL)
		{
			char s_id[10];
			char section[2];
			if (counter == 0)
			{
				strcpy(s_id, ptr);
			}
			if (strcmp(SECTION, ptr) == 0)
			{
				strcpy(section, ptr);
				float sum = 0;
				while (counter < 6)
				{
					sum += atoi(ptr);
					ptr = strtok(NULL, ",");
					counter++;
				}
				sum /= 4;
				// printf("Student of ID %s from section %s have average marks %.2f\n",s_id,section,sum);
				char str_sum[10];
				gcvt(sum, 10, str_sum); //convert float to string
				write(1, s_id, strlen(s_id));
				write(1, "\t\t", sizeof("\t\t"));
				write(1, section, strlen(section));
				write(1, "\t\t", sizeof("\t\t"));
				write(1, str_sum, strlen(str_sum));
				write(1, "\n", sizeof("\n") - 1);
				counter = 0;
				break;
			}
			ptr = strtok(NULL, ",");
			counter++;
		}
	}
	fclose(file);
}
int main()
{
	pid_t pid;
	int status;
	pid = fork();
	if (pid == 0)
	{
		write(1, "child process\n\n", sizeof("child process\n\n"));
		process("A");
	}
	else if (pid > 0)
	{
		waitpid(pid, &status, 0);
		write(1, "\n\nparent process\n\n", sizeof("\n\nparent process\n\n"));
		process("B");
	}
	else{
		write(1,"Process cant be created",strlen("Process cant be created"));
	}
	return 0;
}