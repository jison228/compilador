include number.asm
include macros2.asm

.MODEL LARGE ;Modelo de Memoria
 .386 ;Tipo de Procesador
.STACK 200h ;Bytes en el Stack

.DATA
    __0 dd  0
    __1 dd  1
    _10 dd  10
    _2  dd  2
    _100    dd  100
    _5  dd  5
    _Si_OK_Siguiente_linea_es_30    db  "Si OK Siguiente linea es 30",'$',27 dup(?)
    _Si_OK_Siguiente_linea_es_40    db  "Si OK Siguiente linea es 40",'$',27 dup(?)
    _El_ifelse_funciona db  "El ifelse funciona",'$',18 dup(?)
    _El_ifelse_no_funciona  db  "El ifelse no funciona",'$',21 dup(?)
    _Si_if_OK_sig_linea_1   db  "Si if OK sig linea 1",'$',20 dup(?)
    _1  db  "1",'$',1 dup(?)
    _El_not_si_funciona db  "El not si funciona",'$',18 dup(?)
    _long_4 dd  4.0
    _Si_long_ok_4   db  "Si long ok 4",'$',12 dup(?)
    _DisplaySolo_OK db  "DisplaySolo OK",'$',14 dup(?)
    _Si_while_ok_un_OK  db  "Si while ok un OK",'$',17 dup(?)
    _25 dd  25
    _OK db  "OK",'$',2 dup(?)
    _Si_for_ok_dos_OK   db  "Si for ok dos OK",'$',16 dup(?)
    _3  dd  3
    _OK_FOR db  "OK FOR",'$',6 dup(?)
    _Si_for_step_ok_un_OK   db  "Si for step ok un OK",'$',20 dup(?)
    _OK_FOR_STEP    db  "OK FOR STEP",'$',11 dup(?)
    a   dd  ?
    b   dd  ?
    d   dd  ?
    f   dd  ?
    c   dd  ?
    e   dd  ?
    nousada dd  ?
    @aux0   dd  ?
    @aux1   dd  ?
    @aux2   dd  ?
    @aux3   dd  ?
    @aux4   dd  ?
    @aux7   dd  ?
    @aux13  dd  ?
    @aux19  dd  ?
    @aux24  dd  ?
    @aux26  dd  ?
    @aux29  dd  ?
    @aux34  dd  ?

.CODE

start:
MOV EAX,@DATA
MOV DS,EAX
MOV ES,EAX


FILD _10
FILD _2
FMUL
FSTP @aux0
FFREE
FLD @aux0
FILD _10
FADD
FSTP @aux1
FFREE
FLD @aux1
FISTP a
FILD _5
FILD _10
FMUL
FSTP @aux2
FFREE
FILD _100
FLD @aux2
FSUB
FSTP @aux3
FFREE
FLD @aux3
FILD _10
FSUB
FSTP @aux4
FFREE
FLD @aux4
FISTP b
DisplayString _Si_OK_Siguiente_linea_es_30
newline 1
DisplayInteger a
newline 1
DisplayString _Si_OK_Siguiente_linea_es_40
newline 1
DisplayInteger b
newline 1
FILD a
FILD b
FCOMP
FSTSW AX
SAHF
FFREE
JA _tag_de_verdadero_@aux5
FILD __0
FSTP @aux7
JMP _tag_de_continuar_@aux6
_tag_de_verdadero_@aux5:
FILD __1
FSTP @aux7
_tag_de_continuar_@aux6:
FLD @aux7
FCOMP __1
FSTSW AX
SAHF
FFREE
JZ _tag_del_else_@aux9
DisplayString _El_ifelse_funciona
newline 1
JMP _tag_del_fin_@aux10
_tag_del_else_@aux9:
DisplayString _El_ifelse_no_funciona
newline 1
_tag_del_fin_@aux10:
DisplayString _Si_if_OK_sig_linea_1
newline 1
FILD b
FILD a
FCOMP
FSTSW AX
SAHF
FFREE
JA _tag_de_verdadero_@aux11
FILD __0
FSTP @aux13
JMP _tag_de_continuar_@aux12
_tag_de_verdadero_@aux11:
FILD __1
FSTP @aux13
_tag_de_continuar_@aux12:
FLD @aux13
FCOMP __1
FSTSW AX
SAHF
FFREE
JZ _tag_del_if_@aux14
DisplayString _1
newline 1
_tag_del_if_@aux14:
FILD b
FILD a
FCOMP
FSTSW AX
SAHF
FFREE
JA _tag_de_verdadero_@aux17
FILD __0
FSTP @aux19
JMP _tag_de_continuar_@aux18
_tag_de_verdadero_@aux17:
FILD __1
FSTP @aux19
_tag_de_continuar_@aux18:
FLD @aux19
FILD __1
FCOMP
FSTSW AX
SAHF
FFREE
JZ _tag_de_verdadero_@aux15
FILD __0
FSTP @aux19
JMP _tag_de_continuar_@aux16
_tag_de_verdadero_@aux15:
FILD __1
FSTP @aux19
_tag_de_continuar_@aux16:
FLD @aux19
FCOMP __1
FSTSW AX
SAHF
FFREE
JZ _tag_del_if_@aux20
DisplayString _El_not_si_funciona
newline 1
_tag_del_if_@aux20:
FLD _long_4
FISTP e
DisplayString _Si_long_ok_4
newline 1
DisplayInteger e
newline 1
DisplayString _DisplaySolo_OK
newline 1
DisplayString _Si_while_ok_un_OK
newline 1
_tag_del_while_@aux21:
FILD b
FILD a
FCOMP
FSTSW AX
SAHF
FFREE
JAE _tag_de_verdadero_@aux22
FILD __0
FSTP @aux24
JMP _tag_de_continuar_@aux23
_tag_de_verdadero_@aux22:
FILD __1
FSTP @aux24
_tag_de_continuar_@aux23:
FLD @aux24
FILD __1
FCOMP
FSTSW AX
SAHF
FFREE
JZ _tag_del_while_continua@aux25
FILD _25
FILD a
FADD
FSTP @aux26
FFREE
FLD @aux26
FISTP a
DisplayString _OK
newline 1
JMP _tag_del_while_@aux21
_tag_del_while_continua@aux25:
DisplayString _Si_for_ok_dos_OK
newline 1
FILD _2
FISTP a
_tag_del_for_@aux30:
FILD a
FILD _3
FCOMP
FSTSW AX
SAHF
FFREE
JLE _tag_de_verdadero_@aux27
FILD __0
FSTP @aux29
JMP _tag_de_continuar_@aux28
_tag_de_verdadero_@aux27:
FILD __1
FSTP @aux29
_tag_de_continuar_@aux28:
FLD @aux29
FILD __1
FCOMP
FSTSW AX
SAHF
FFREE
JZ _tag_del_for_continua@aux31
DisplayString _OK_FOR
newline 1
FILD a
FILD __1
FADD
FISTP a
JMP _tag_del_for_@aux30
_tag_del_for_continua@aux31:
DisplayString _Si_for_step_ok_un_OK
newline 1
FILD _2
FISTP a
_tag_del_for_@aux35:
FILD a
FILD _3
FCOMP
FSTSW AX
SAHF
FFREE
JGE _tag_de_verdadero_@aux32
FILD __0
FSTP @aux34
JMP _tag_de_continuar_@aux33
_tag_de_verdadero_@aux32:
FILD __1
FSTP @aux34
_tag_de_continuar_@aux33:
FLD @aux34
FILD __1
FCOMP
FSTSW AX
SAHF
FFREE
JZ _tag_del_for_continua@aux36
DisplayString _OK_FOR_STEP
newline 1
FILD a
FILD _2
FISTP a
JMP _tag_del_for_@aux35
_tag_del_for_continua@aux36:
MOV EAX, 4C00h
INT 21h

END start
