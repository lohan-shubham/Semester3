# run2:
# 	gcc q2a.c -o q2a
# 	gcc q2b.c -o q2b
# 	gcc q2c.c -o q2c
# 	./q2a
# 	./q2b
# 	./q2c
# 	rm q2a q2b q2c
# run3:
# 	gcc q3a.c -o q3a
# 	gcc q3b.c -o q3b
# 	gcc q3c.c -o q3c
CC=gcc
CFLAGS=-I.	
DEPS = 
OBJ = q1.o q2a.o q2b.o q2c.o q3a.o q3b.o q3c.o q6.o  

%.o: %.c $(DEPS)
	$(CC) -o $@ $< $(CFLAGS) -pthread

all: $(OBJ)

clean:
	rm -f $(OBJ)