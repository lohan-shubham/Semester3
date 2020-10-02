global _start

section .data
res1 db '1',0xA,0xD
res2 db '0',0xA,0xD
x dd '50'
y dd '100'
section .text
_start:
      mov edi,[x]    ;storing x in register
      mov esi,[y]    ;storing y in register
      call checkGreater
  
      checkGreater:
             sub edi,esi
             jnl xgreater
             jng ygreater
        
      xgreater:
           mov rdx,1
           mov rcx,res1
           mov rbx,1
           mov rax,4
           int 0x80
           mov rax,60
           xor rdi,rdi
           syscall
      ygreater:
           mov rdx,1
           mov rcx,res2
           mov rbx,1
           mov rax,4
           int 0x80
           mov rax,60
           xor rdi,rdi
           syscall
      


               

