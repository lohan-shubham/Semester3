default:
		gcc -E Main.c -o Main.i
		gcc -S Main.i -o Main.s
		gcc -c Main.s -o Main.o
		gcc Main.o -o Main
		./Main
		rm Main *.o *.i *.s