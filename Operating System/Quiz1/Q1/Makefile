default:
	nasm -f elf64 -o P1.o P1.asm && ld -o P1 P1.o && ./P1
	rm *.o P1
part2:
	nasm -felf64 P1.asm && gcc P1.c P1.o && ./a.out
	rm P1.o a.out
