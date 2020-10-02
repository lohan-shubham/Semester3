	global add
	section .text

add:
	mov eax,edi
	mov ebx,esi
	add eax,ebx
	ret