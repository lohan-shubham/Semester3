64bit: 
	nasm -felf64 P4_2.asm && gcc P4.c P4_2.o
	./a.out
	rm P4_2.o a.out
32bit:
	nasm -felf32 P4_1.asm && gcc -m32 P4.c P4_1.o
	./a.out
	rm a.out P4_1.o