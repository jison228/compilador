package Analizadores;
import java_cup.runtime.*;

/* Directivas */
%%     

%public
%class AnalizadorLexico
%cupsym Simbolos
%cup
%column
%line
%ignorecase
%unicode

%{
    int RANGO_ENTERO = (int) (Math.pow(2, 16)-1);
    float RANGO_FLOAT = (float) (Math.pow(2, 32)-1);
    int RANGO_STRING = 30;
    int RANGO_IDENTIFICADOR = 256;
    private Symbol symbol(int type) {
          System.out.println("[LEX] TOKEN < " + Simbolos.terminalNames[type] + " > : " + yytext());
          return new Symbol(type, yyline, yycolumn, yytext());
    }
    private Symbol symbol(int type, Object value) {
          return new Symbol(type, yyline, yycolumn, value);
    }
%}

/* Declaraciones de REGEX utiles */
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
DIGITO 	    =	[0-9]
LETRA 	    =	[a-zA-Z]

// Construcciones del lenguaje
COMMENT = "*/" ~"/*"
IDENTIFICADOR = {LETRA}[a-zA-Z0-9]*

// Constantes
CONSTANTE_ENTERA =  {DIGITO}+
CONSTANTE_FLOAT =  {DIGITO}+"."{DIGITO}* | {DIGITO}*"."{DIGITO}+
CONSTANTE_STRING =  \".*\"

// Palabras reservadas
IF = "if" | "IF"
ELSE = "else" | "ELSE"
WHILE = "while" | "WHILE"
DIM = "DIM" | "dim"
DISPLAY = "DISPLAY" | "display"
GET = "GET" | "get"
INTEGER_TYPE = "integer" | "INTEGER"
FLOAT_TYPE = "real" | "REAL"
STRING_TYPE = "string" | "STRING"
FOR = "FOR" | "for"
NEXT = "NEXT" | "next"
TO = "TO" | "to"
LONG = "long" | "LONG"
ENDIF = "ENDIF" | "endif"
ENDWHILE = "ENDWHILE" | "endwhile"

// Operadores lógicos y ariméticos
OP_GT = ">" 
OP_LT = "<" 
OP_GTE = ">=" 
OP_LTE = "<=" 
OP_NE = "!="
OP_PLUS = "+" 
OP_MINUS = "-" 
OP_MULTI = "*" 
OP_DIVISION = "/"
OP_NOT = "!" 
OP_AND = "&&"
OP_OR = "||" 
OP_ASIG = ":="
OP_EQ = "=="
OP_TIPO = "AS"

// Caracteres especiales
COMA = ","
PAREN_OPEN = "("
PAREN_CLOSE = ")"
CORCHETE_OPEN = "["
CORCHETE_CLOSE = "]"

%%
<YYINITIAL> {

// Keywords
{IF}	                     { return symbol(Simbolos.IF); }
{ELSE}	                 	 { return symbol(Simbolos.ELSE); }
{WHILE}	                	 { return symbol(Simbolos.WHILE); }
{DIM}	                 	 { return symbol(Simbolos.DIM); }
{DISPLAY}	                 { return symbol(Simbolos.DISPLAY); }
{GET}	               		 { return symbol(Simbolos.GET); }
{INTEGER_TYPE}	             { return symbol(Simbolos.INTEGER_TYPE); }
{FLOAT_TYPE}	             { return symbol(Simbolos.FLOAT_TYPE); }
{STRING_TYPE}	             { return symbol(Simbolos.STRING_TYPE); }
{FOR}						 { return symbol(Simbolos.FOR); }
{NEXT}						 { return symbol(Simbolos.NEXT); }
{TO}						 { return symbol(Simbolos.TO); }
{LONG}						 { return symbol(Simbolos.LONG); }
{ENDIF}						 { return symbol(Simbolos.ENDIF); }
{ENDWHILE}					 { return symbol(Simbolos.ENDWHILE); }

// Operadores

{OP_GT}                      { return symbol(Simbolos.OP_GT); }
{OP_LT}                      { return symbol(Simbolos.OP_LT); }
{OP_GTE}                     { return symbol(Simbolos.OP_GTE); }
{OP_LTE}                     { return symbol(Simbolos.OP_LTE); }
{OP_NE}                      { return symbol(Simbolos.OP_NE); }
{OP_PLUS}                    { return symbol(Simbolos.OP_PLUS); }
{OP_MINUS}                   { return symbol(Simbolos.OP_MINUS); }
{OP_MULTI}                   { return symbol(Simbolos.OP_MULTI); }
{OP_DIVISION}                { return symbol(Simbolos.OP_DIVISION); }
{OP_NOT}                     { return symbol(Simbolos.OP_NOT); }
{OP_AND}                     { return symbol(Simbolos.OP_AND); }
{OP_OR}                      { return symbol(Simbolos.OP_OR); }
{OP_ASIG}                    { return symbol(Simbolos.OP_ASIG); }
{OP_EQ}                      { return symbol(Simbolos.OP_EQ); }
{OP_TIPO}                    { return symbol(Simbolos.OP_TIPO); }

// Caracteres especiales
{COMA}                         { return symbol(Simbolos.COMA); }
{PAREN_OPEN}                   { return symbol(Simbolos.PAREN_OPEN); }
{PAREN_CLOSE}                  { return symbol(Simbolos.PAREN_CLOSE); }
{CORCHETE_OPEN}                { return symbol(Simbolos.CORCHETE_OPEN); }
{CORCHETE_CLOSE}               { return symbol(Simbolos.CORCHETE_CLOSE); }


{COMMENT}	                 { /* do nothing */ }
{IDENTIFICADOR}	           { 
                                    String id = new String(yytext());
                                    int length = id.length();

                                    if(length <= RANGO_IDENTIFICADOR ){
                                          return symbol(Simbolos.IDENTIFICADOR); 
                                    }                                          
                                    else
                                    {
                                          System.err.println("El identificador [" + yytext() + "] esta fuera del limite de los los identificadores. (Se obtuvo " + length + ", maximo " + RANGO_IDENTIFICADOR + ")");                                    
                                          System.in.read();
                                          throw new Error("El identificador [" + yytext() + "] esta fuera del limite de los los identificadores. (Se obtuvo " + length + ", maximo " + RANGO_IDENTIFICADOR + ")");                                    
                                    }
                             }

{CONSTANTE_ENTERA}	     {                             
                                    Integer constInt = Integer.parseInt(yytext());

                                    if(Math.abs(constInt) <= RANGO_ENTERO ){
                                          return symbol(Simbolos.CONSTANTE_ENTERA);
                                    }                                          
                                    else
                                    {
                                          System.err.println("La constante [" + yytext() + "] esta fuera del limite de los enteros. (Se obtuvo " + constInt + ", maximo " + RANGO_ENTERO + ")");
                                          System.in.read();
                                          throw new Error("La constante [" + yytext() + "] esta fuera del limite de los enteros. (Se obtuvo " + constInt + ", maximo " + RANGO_ENTERO + ")"); 
                                    }
                             }
{CONSTANTE_FLOAT}            {
                                    Double constFloat = Double.parseDouble(yytext());
                                    if (Math.abs(constFloat) <= RANGO_FLOAT)
                                          return symbol(Simbolos.CONSTANTE_FLOAT);
                                    else
                                    {
                                          System.err.println("La constante [" + yytext() + "] esta fuera del limite de los flotantes. (Se obtuvo " + constFloat + ", maximo " + RANGO_FLOAT + ")");
                                          System.in.read();
                                          throw new Error("La constante [" + yytext() + "] esta fuera del limite de los flotantes. (Se obtuvo " + constFloat + ", maximo " + RANGO_FLOAT + ")");
                                    }
                              }

{CONSTANTE_STRING}            { 
                                    String constString = new String(yytext());
                                    // Restamos 2 por las comillas
                                    if (constString.length()-2 <= RANGO_STRING)
                                          return symbol(Simbolos.CONSTANTE_STRING); 
                                    else
                                    {
                                          System.err.println("La constante [" + yytext() + "] excede el largo permitido para un string. (Se obtuvo " + constString.length() + ", maximo " + RANGO_STRING + ")");
                                          System.in.read();
                                          throw new Error("La constante [" + yytext() + "] excede el largo permitido para un string. (Se obtuvo " + constString.length() + ", maximo " + RANGO_STRING + ")");
                                    } 
                              }                              
{WhiteSpace}                  { /* do nothing */ }

}

//--------> Errores Lexicos
[^]   {
            System.out.println("Error Léxico: --> " + yytext() + " <-- Linea " + (yyline+1) + " Columna " + yycolumn);
            throw new Error("Error léxico");
      }
<<EOF>>                          { return symbol(Simbolos.EOF); }