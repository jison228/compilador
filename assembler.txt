include number.asm
include macros2.asm

.MODEL LARGE ;Modelo de Memoria
 .386 ;Tipo de Procesador
.STACK 200h ;Bytes en el Stack

.DATA
 	__0	dd	0.0
	__1	dd	1.0
	_30_caracteres_es_muy_poco	db	"30 caracteres es muy poco",'$',25 dup(?)
	a	dd	?
	b	dd	?
	c	dd	?
	e	dd	?

DisplayString _30_caracteres_es_muy_poco
newline 1
MOV EAX, 4C00h
INT 21h

END start
