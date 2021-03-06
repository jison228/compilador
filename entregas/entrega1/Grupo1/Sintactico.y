package Analizadores;
import java_cup.runtime.*;
import SymbolTable.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

// Codigo que se le agrega a las acciones gramaticales
action code
{:
     public SymbolTable tablaDeSimbolos = new SymbolTable();
     public ArrayList<String> identifierList = new ArrayList();
:}

// Codigo que se le agrega al parser
parser code     
{:
    public void syntax_error(Symbol cur_token) {
        String errMsg = "Syntax error -> " + cur_token.value.toString() + " <- " + cur_token.left + ":" +  cur_token.right + "\n";
        report_error(errMsg, null);
    }

    public void unrecovered_syntax_error(Symbol cur_token) throws Exception {
        String errMsg = "Couldn't repair and continue parse ->" + cur_token.value.toString() + "<- " + cur_token.left + ":" +  cur_token.right + "\n";
        report_fatal_error(errMsg, null);
    }
:}

// Declaracion de terminales de constantes
terminal String CONSTANTE_ENTERA, CONSTANTE_FLOAT, CONSTANTE_STRING;

// Declaracion de terminales de palabras reservadas
terminal String IF, ELSE, WHILE, DIM, DISPLAY, GET, INTEGER_TYPE, FLOAT_TYPE, STRING_TYPE, FOR, NEXT, TO, LONG, ENDIF, ENDWHILE;

// Declaracion de terminales de operadores lógicos
terminal String OP_GT, OP_LT, OP_GTE, OP_LTE, OP_PLUS, OP_MINUS, OP_MULTI, OP_DIVISION,
                OP_NE, OP_NOT, OP_AND, OP_OR, OP_ASIG, OP_EQ, OP_TIPO;

// Declaracion de terminales de caracteres especiales
terminal String COMA, PAREN_OPEN, PAREN_CLOSE, CORCHETE_OPEN, CORCHETE_CLOSE;

// Declaracion de construcciones del lenguaje
terminal String IDENTIFICADOR;

// Declaracion de no terminales
non terminal Symbol inicio, programa, declaracion, tiposdedato ,tipodedato, identificadores, sentencia, asignacion, expresion, termino, factor, 
                    condicion, comparacion, if, while, display, get, for, long;

start with inicio;

//Reglas gramaticales
inicio         	::= programa
                    {:
                         tablaDeSimbolos.save();
                         System.out.println("Compilacion exitosa.");
                    :};  

programa      		::= programa sentencia
                    | sentencia;

sentencia           ::= declaracion
                    {:
                         System.out.println("sentencia -> declaracion");
                    :}
                    | asignacion 
                    {:
                         System.out.println("sentencia -> asignacion");
                    :}
                    | if 
                    {:
                         System.out.println("sentencia -> if");
                    :}
                    | while 
                    {:
                         System.out.println("sentencia -> while");
                    :}
                    | display 
                    {:
                         System.out.println("sentencia -> display");
                    :}
                    | get 
                    {:
                         System.out.println("sentencia -> get");
                    :}
                    | for 
                    {:
                         System.out.println("sentencia -> for");
                    :}
                    | long 
                    {:
                         System.out.println("sentencia -> long");
                    :};

/*
            DECLARACIONES
*/

declaracion         ::= DIM CORCHETE_OPEN identificadores CORCHETE_CLOSE OP_TIPO CORCHETE_OPEN tiposdedato CORCHETE_CLOSE
                    {: 
                         System.out.println("declaracion -> DIM declaracion ");
                    :};

tiposdedato          ::= tipodedato
                    {:
                         System.out.println("tiposdedato -> Tipo de dato simple ");
                    :}
                    | tiposdedato COMA tipodedato
                    {:
                         System.out.println("tiposdedato -> Tipo de dato en lista ");
                    :};
                    
tipodedato          ::= INTEGER_TYPE
                    {:
                         tablaDeSimbolos.addIdentifiers(this.identifierList, "INTEGER");
                    :}
                    | FLOAT_TYPE
                    {:
                         tablaDeSimbolos.addIdentifiers(this.identifierList, "FLOAT");                       
                    :}
                    | STRING_TYPE
                    {:
                         tablaDeSimbolos.addIdentifiers(this.identifierList, "STRING");                         
                    :};

identificadores     ::= IDENTIFICADOR:ID
                    {:
                         this.identifierList.add(ID);
                    :}
                    | identificadores COMA IDENTIFICADOR:ID
                    {:
                         this.identifierList.add(ID);
                    :};
/*
            ASIGNACION
*/
asignacion          ::= IDENTIFICADOR OP_ASIG expresion
                    {:
                         System.out.println("asignacion -> IDENTIFICADOR OP_ASIG expresion");
                    :};

expresion           ::= expresion OP_PLUS termino 
                    | expresion OP_MINUS termino 
                    | termino;

termino             ::= termino OP_MULTI factor
                    | termino OP_DIVISION factor
                    | factor;

factor              ::= CONSTANTE_ENTERA:CTE_ENT
                    {:
                         tablaDeSimbolos.add("_"+CTE_ENT, null, CTE_ENT, null);
                    :}
                    | CONSTANTE_FLOAT:CTE_FLOAT
                    {:
                         tablaDeSimbolos.add("_"+CTE_FLOAT, null, CTE_FLOAT, null);                         
                    :} 
                    | CONSTANTE_STRING:CTE_STRING
                    {:
                         String str = CTE_STRING.replace("\"", "");
                         tablaDeSimbolos.add("_"+str, null, str, str.length());
                    :}
                    | PAREN_OPEN expresion PAREN_CLOSE 
                    | IDENTIFICADOR;

/*
            IF/ELSE/CONDITION
*/
if                  ::= IF PAREN_OPEN condicion PAREN_CLOSE programa ENDIF
                    {:
                         System.out.println("if -> IF PAREN_OPEN condicion PAREN_CLOSE programa ENDIF");
                    :}
                    | IF PAREN_OPEN condicion PAREN_CLOSE programa ELSE programa ENDIF
                    {:
                         System.out.println("if -> IF PAREN_OPEN condicion PAREN_CLOSE programa ELSE programa ENDIF");
                    :};

condicion           ::= comparacion OP_AND comparacion 
                    | comparacion OP_OR comparacion
                    | OP_NOT PAREN_OPEN comparacion PAREN_CLOSE
                    | comparacion;

comparacion         ::= expresion OP_GT expresion 
                    | expresion OP_LT expresion
                    | expresion OP_GTE expresion 
                    | expresion OP_LTE expresion
                    | expresion OP_EQ expresion
                    | expresion OP_NE expresion;

/*
            WHILE
*/

while               ::= WHILE PAREN_OPEN condicion PAREN_CLOSE programa ENDWHILE
                    {:
                         System.out.println("while -> WHILE PAREN_OPEN condicion PAREN_CLOSE programa ENDWHILE");
                    :};
/*
            display
*/
display             ::= DISPLAY expresion
                    {:
                         System.out.println("display -> display expresion");
                    :};
/*
            get
*/
get                ::= GET IDENTIFICADOR
                    {:
                         System.out.println("get -> get IDENTIFICADOR");
                    :};
/*
            for
*/
for                ::= FOR asignacion TO expresion programa NEXT IDENTIFICADOR
                    {:
                         System.out.println("for -> for asignacion TO expresion programa NEXT identificador");
                    :}
					| FOR asignacion TO expresion CORCHETE_OPEN CONSTANTE_ENTERA CORCHETE_CLOSE programa NEXT IDENTIFICADOR
                    {:
                         System.out.println("for -> FOR asignacion TO expresion CORCHETE_OPEN CONSTANTE_ENTERA CORCHETE_CLOSE programa NEXT identificador");
                    :};
/*
            long
*/
long                ::= LONG PAREN_OPEN CORCHETE_OPEN identificadores CORCHETE_CLOSE PAREN_CLOSE
                    {:
                         System.out.println("long -> LONG PAREN_OPEN CORCHETE_OPEN identificadores CORCHETE_CLOSE PAREN_CLOSE");
                    :};
