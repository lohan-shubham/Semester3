;Name: Shubham Lohan
;Roll_Number: 2019275

bits 16
org 31744
; Assembly directive
boot:
	mov ax, 9217
	int 21   ;BIOS memory 32bit
	mov ax, 3
	int 16  ;used for screen manuplation
	cli
	lgdt [gdt_pointer]
	mov eax, cr0
	or eax,1
	mov cr0, eax
	jmp CODE_SEG:boot2
;main code starts from here
gdt_start:
	DQ 0
;code starts here

gdt_code:
	DW 65535
	DW 0
	DB 0
	DB 154
	DB 207
	DB 0
gdt_data:
	DW 65535
	DW 0
	DB 0
	DB 146
	DB 207
	DB 0

gdt_end:

gdt_pointer:
	DW gdt_end - gdt_start
	dd gdt_start

CODE_SEG equ gdt_code - gdt_start
DATA_SEG equ gdt_data - gdt_start

bits 32

boot2: 
	;change to protected mode
	mov ax, DATA_SEG
	mov esi,hello ;contains string value of message
	mov ebx,753664
.loop:
	lodsb
	or al,al
	jz protectedMode
	or eax,256
	mov word [ebx], ax
	add ebx,1
	add ebx,1
	jmp .loop

protectedMode:
    mov edx, cr0 ; store value of CR0 into register
    mov ecx, 32    ; for 32 bit 
.loop2:
    mov eax, 304; to print content of the CR0 register  
    shl edx, 1           
    adc eax, 0           
    mov [ebx], ax
    add ebx, 1
    add ebx, 1
    sub ecx,1
    jnz .loop2
halt:
    cli
    hlt
    jmp halt
    
hello: DB "Hello world! ",0

times 510 - ($-$$) DB 0

DW 43605

