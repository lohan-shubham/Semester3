/*Name: Shubham Lohan
Roll No: 2019275*/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

FILE *fptr1;
FILE *fptr2;
FILE *fptr3;
char *fileName;
char *fileName1;
void unlock() {
	char del[100] = "rm ";
	strcat(del, fileName1);
	system(del);
}
void lock() {
	fptr2 = fopen(fileName1, "w");
	fprintf(fptr2, "%c", 't');
	fclose(fptr2);
}
void writedata() {
	char input[100];
	fptr1 = fopen(fileName, "a+");
	gets(input);
	gets(input);
	while (strlen(input) > 0) {
		fprintf(fptr1, "%s\n", input );
		fclose(fptr1);
		fptr1 = fopen(fileName, "a+");
		gets(input);
	}
	fclose(fptr1);
}
void script() {
	char cmd[100] = "subl ";
	strcat(cmd, fileName);

	fptr3 = fopen("checker.sh", "w");
	fprintf(fptr3, "%s\n", cmd);
	fclose(fptr3);
	system("chmod +x checker.sh");
}
int main()
{

	system("clear");
	printf("Enter the file name : ");
	fileName = (char *)malloc(sizeof(char) * 1024);
	fileName1 = (char *)malloc(sizeof(char) * 1024);
	scanf("%s", fileName);
	system("clear");
	strcpy(fileName1, fileName);
	strcat(fileName, ".txt");
	strcat(fileName1, "_Adlock.txt");
	fptr2 = fopen(fileName1, "a+");
	char data[10];
	fgets(data, 10, fptr2);
	int checker = 0;
	script();
	while (data[checker] != '\0')
		checker++;
	if (checker != 0) {
		printf("Can't open file!,used by another program!!!\n");
		printf("Data written might be crrupted or changed \n");
		writedata();
		return 0;
	}
	lock();
	writedata();
	unlock();
	fptr3 = fopen("checker.sh", "w");
	fprintf(fptr3, "%s\n", "subl *.txt");
	system("chmod +x checker.sh");
}
