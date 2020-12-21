compile:
	nasm -f bin Q1_2019275.asm -o boot.bin
	

run: compile
	qemu-system-x86_64 -fda boot.bin 
	

