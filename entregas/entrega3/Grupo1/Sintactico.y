package Analizadores;
import java_cup.runtime.*;
import SymbolTable.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import com.unlam.compilador.*;
import java.util.Stack;

// Codigo que se le agrega a las acciones gramaticales
action code
{:
     public SymbolTable tablaDeSimbolos = new SymbolTable();
     public ArrayList<String> identifierList = new ArrayList();
     public ArrayList<String> typesList = new ArrayList();
     public Stack<Nodo> punteroFactor = new Stack<Nodo>();
     public Stack<Nodo> punteroTermino = new Stack<Nodo>();
     public Stack<Nodo> punteroExpresion = new Stack<Nodo>();
     
     public NodoIntermedio punteroInicio = null;
     public Stack<Nodo> punteroPrograma = new Stack<Nodo>();
     public Nodo punteroSentencia = null;
     public Nodo punteroDeclaracion = null;
     public Nodo punteroTiposDeDatos = null;
     public Nodo punteroTipoDeDato = null;
     public Nodo punteroIdentificadores = null;
     public Nodo punteroAsignacion = null;
     public Nodo punteroIf = null;
     public Nodo punteroCondicion = null;
     public Nodo punteroComparacion = null;
     public Nodo punteroWhile = null;
     public Nodo punteroDisplay = null;
     public Nodo punteroGet = null;
     public Nodo punteroFor = null;
     public Nodo punteroLong = null;
     
     public Nodo auxElse = null;     
     public Nodo auxComparacion = null; // renombrar condicion
     public Nodo auxComparacion2 = null;
     public Nodo auxTo = null;
     public Nodo auxBy = null;
     public Nodo auxFor = null;
     
     public int IdsCount = 0;
     
     public String auxTipo;
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

// Declaracion de terminales de operadores l??gicos
terminal String OP_GT, OP_LT, OP_GTE, OP_LTE, OP_PLUS, OP_MINUS, OP_MULTI, OP_DIVISION,
                OP_NE, OP_NOT, OP_AND, OP_OR, OP_ASIG, OP_EQ, OP_TIPO;

// Declaracion de terminales de caracteres especiales
terminal String COMA, PAREN_OPEN, PAREN_CLOSE, CORCHETE_OPEN, CORCHETE_CLOSE;

// Declaracion de construcciones del lenguaje
terminal String IDENTIFICADOR;

// Declaracion de no terminales
non terminal Symbol inicio, programa, declaracion, tiposdedato ,tipodedato, identificadores, sentencia, asignacion, expresion, termino, factor, 
                    condicion, comparacion, if, while, display, get, for, long, identificadoresLong;

start with inicio;

//Reglas gramaticales
inicio         	::= programa
                    {:
                    	 tablaDeSimbolos.addIdentifiers(this.identifierList, this.typesList);  
                    	 punteroInicio = (NodoIntermedio)punteroPrograma.pop();
                         tablaDeSimbolos.save();
                         punteroInicio.save();
                         punteroInicio.GenerarAsembler(tablaDeSimbolos);
                         System.out.println("Compilacion exitosa.");
                    :};  

programa      		::= programa sentencia
                    {:
                    	punteroPrograma.push( new NodoIntermedio("SIG", punteroPrograma.pop(), punteroSentencia));
                    :}
                    | sentencia
                    {:
	                     punteroPrograma.push(punteroSentencia);
                    :};

sentencia           ::= declaracion
                    {:
                         System.out.println("sentencia -> declaracion");
	                     punteroSentencia = punteroDeclaracion;
                         System.out.println("se apunto punteroSentencia a: " + punteroDeclaracion.getVal());
                    :}
                    | asignacion 
                    {:
	                     punteroSentencia = punteroAsignacion;
                         System.out.println("sentencia -> asignacion");
                    :}
                    | if 
                    {:
	                     punteroSentencia = punteroIf;
                         System.out.println("sentencia -> if");
                    :}
                    | while 
                    {:
	                     punteroSentencia = punteroWhile;
                         System.out.println("sentencia -> while");
                    :}
                    | display 
                    {:
	                     punteroSentencia = punteroDisplay;
                         System.out.println("sentencia -> display");
                    :}
                    | get 
                    {:
	                     punteroSentencia = punteroGet;
                         System.out.println("sentencia -> get");
                    :}
                    | for 
                    {:
	                     punteroSentencia = punteroFor;
                         System.out.println("sentencia -> for");
                    :};

/*
            DECLARACIONES
*/

declaracion         ::= DIM CORCHETE_OPEN identificadores CORCHETE_CLOSE OP_TIPO CORCHETE_OPEN tiposdedato CORCHETE_CLOSE
                    {: 
                         System.out.println("declaracion -> DIM declaracion ");
                         punteroDeclaracion = new Hoja("declaracion");
                         System.out.println("se apunto punteroDeclaracion a: " + punteroDeclaracion.getVal());
                    :};

tiposdedato         ::= tipodedato
                    {:
                         System.out.println("tiposdedato -> Tipo de dato simple ");
                    :}
                    | tiposdedato COMA tipodedato
                    {:
                         System.out.println("tiposdedato -> Tipo de dato en lista ");
                    :};
                    
tipodedato          ::= INTEGER_TYPE
                    {:
                         //tablaDeSimbolos.addIdentifiers(this.identifierList, "INTEGER");
                         this.typesList.add("CTE_INTEGER");
                    :}
                    | FLOAT_TYPE
                    {:
                         //tablaDeSimbolos.addIdentifiers(this.identifierList, "FLOAT");  
                         this.typesList.add("CTE_FLOAT");                     
                    :}
                    | STRING_TYPE
                    {:
                         //tablaDeSimbolos.addIdentifiers(this.identifierList, "STRING");  
                         this.typesList.add("CTE_STRING");                       
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
asignacion          ::= IDENTIFICADOR:ID OP_ASIG expresion
                    {:
                    	if(!this.identifierList.contains(ID)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+"' no ha sido declarado."); 
                    	}
                    	
                    	
                    	String tipoId = this.typesList.get(this.identifierList.indexOf(ID));
                    	if(!tipoId.equals(auxTipo)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+" : " + tipoId +"' no es asignable a " + auxTipo); 
                    	}
                    	
                    	System.out.println("asig numero -> " + auxTipo);
                    	                    	
                    	punteroAsignacion = new NodoIntermedio("OP_ASIG", new Hoja(ID), punteroExpresion.pop());
                         System.out.println("asignacion -> IDENTIFICADOR OP_ASIG expresion");
                    :};

expresion           ::= expresion OP_PLUS termino 
                    {:
                    	punteroExpresion.push(new NodoIntermedio("OP_PLUS", punteroExpresion.pop(), punteroTermino.pop()));
                    	System.out.println("se apunto punteroExpresion a: ");                    	
                    	
                    :}
                    | expresion OP_MINUS termino 
                    {:
                    	punteroExpresion.push( new NodoIntermedio("OP_MINUS", punteroExpresion.pop(), punteroTermino.pop()));
                    :}
                    | termino
                    {:
                    	punteroExpresion.push( punteroTermino.pop());
                    	System.out.println("se apunto punteroExpresion a: " );
                    :};

termino             ::= termino OP_MULTI factor
                    {:
                    	punteroTermino.push( new NodoIntermedio("OP_MULTI", punteroTermino.pop(), punteroFactor.pop()));
                    :}
                    | termino OP_DIVISION factor
                    {:
                    	punteroTermino.push( new NodoIntermedio("OP_DIVISION", punteroTermino.pop(), punteroFactor.pop()));
                    :}
                    | factor
                    {:
                    	punteroTermino.push( punteroFactor.pop());
                    :};

factor              ::= CONSTANTE_ENTERA:CTE_ENT
                    {:
                      	 auxTipo = "CTE_INTEGER";
	                     punteroFactor.push(new Hoja("_"+CTE_ENT));
                         System.out.println("se apunto punteroFactor a: " + CTE_ENT);
                         tablaDeSimbolos.add("_"+CTE_ENT, "CTE_INTEGER", CTE_ENT, null);
                    :}
                    | CONSTANTE_FLOAT:CTE_FLOAT
                    {:
                    	 auxTipo = "CTE_FLOAT";
	                     punteroFactor.push(new Hoja("_"+CTE_FLOAT));
                         System.out.println("se apunto punteroFactor a: " + CTE_FLOAT);
                         tablaDeSimbolos.add("_"+CTE_FLOAT, "CTE_FLOAT", CTE_FLOAT, null);                         
                    :} 
                    | CONSTANTE_STRING:CTE_STRING
                    {:
                    	 auxTipo = "CTE_STRING";
                         System.out.println("se apunto punteroFactor a: " + CTE_STRING);
                         String str = CTE_STRING.replace("\"", "");
	                     punteroFactor.push( new Hoja("_"+str.replace(" ", "_")));
                         tablaDeSimbolos.add("_"+str.replace(" ", "_"), "CTE_STRING", str, str.length());
                    :}
                    | PAREN_OPEN expresion PAREN_CLOSE 
                    {:
	                     punteroFactor.push(punteroExpresion.pop());
                         System.out.println("se apunto punteroFactor a: " );
                    :}
                    | IDENTIFICADOR:ID
                    {:
                    	if(!this.identifierList.contains(ID)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+"' no ha sido declarado."); 
                    	}
                    	auxTipo = this.typesList.get(this.identifierList.indexOf(ID));
	                    punteroFactor.push( new Hoja(ID));
                        System.out.println("se apunto punteroFactor a: " + ID);
                    :}
                    | long 
                    {:
	                     punteroFactor.push( punteroLong);
                         System.out.println("factor -> long");
                    :};

/*
            IF/ELSE/CONDITION
*/
if                  ::= IF PAREN_OPEN condicion PAREN_CLOSE programa ENDIF
                    {:
                    	punteroIf = new NodoIntermedio("IF", punteroCondicion, punteroPrograma.pop());
                         System.out.println("if -> IF PAREN_OPEN condicion PAREN_CLOSE programa ENDIF");
                    :}
                    | IF PAREN_OPEN condicion PAREN_CLOSE programa {: auxElse = punteroPrograma.pop(); RESULT= new Symbol(-1); :} ELSE programa ENDIF
                    {:
                    	punteroIf = new NodoIntermedio("IF", punteroCondicion, 
                    	  new NodoIntermedio("True/False", auxElse, punteroPrograma.pop())
                    	);
                         System.out.println("if -> IF PAREN_OPEN condicion PAREN_CLOSE programa ELSE programa ENDIF");
                    :};

condicion           ::= comparacion {: auxComparacion = punteroComparacion; RESULT= new Symbol(-1); :} OP_AND comparacion 
                    {:
                    	punteroCondicion = new NodoIntermedio("OP_AND", auxComparacion, punteroComparacion);
                    :}
                    | comparacion {: auxComparacion = punteroComparacion; RESULT= new Symbol(-1); :} OP_OR comparacion
                    {:
                    	punteroCondicion = new NodoIntermedio("OP_OR", auxComparacion, punteroComparacion);
                    :}
                    | OP_NOT PAREN_OPEN comparacion PAREN_CLOSE
                    {:
                    	punteroCondicion = new NodoIntermedio("OP_NOT", new Hoja("NOP"), punteroComparacion);
                    :}
                    | comparacion
                    {:
                    	punteroCondicion = punteroComparacion;
                    :};

comparacion         ::= expresion OP_GT {: auxComparacion2 = punteroExpresion.pop(); RESULT= new Symbol(-1); :} expresion 
                    {:
                    	punteroComparacion = new NodoIntermedio("OP_GT", auxComparacion2, punteroExpresion.pop());
                    :}
                    | expresion OP_LT{: auxComparacion2 = punteroExpresion.pop(); RESULT= new Symbol(-1); :} expresion
                    {:
                    	punteroComparacion = new NodoIntermedio("OP_LT", auxComparacion2, punteroExpresion.pop());
                    :}
                    | expresion OP_GTE{: auxComparacion2 = punteroExpresion.pop(); RESULT= new Symbol(-1); :} expresion 
                    {:
                    	punteroComparacion = new NodoIntermedio("OP_GTE", auxComparacion2, punteroExpresion.pop());
                    :}
                    | expresion OP_LTE{: auxComparacion2 = punteroExpresion.pop(); RESULT= new Symbol(-1); :} expresion
                    {:
                    	punteroComparacion = new NodoIntermedio("OP_LTE", auxComparacion2, punteroExpresion.pop());
                    :}
                    | expresion OP_EQ{: auxComparacion2 = punteroExpresion.pop(); RESULT= new Symbol(-1); :} expresion
                    {:
                    	punteroComparacion = new NodoIntermedio("OP_EQ", auxComparacion2, punteroExpresion.pop());
                    :}
                    | expresion OP_NE{: auxComparacion2 = punteroExpresion.pop(); RESULT= new Symbol(-1); :} expresion
                    {:
                    	punteroComparacion = new NodoIntermedio("OP_NE", auxComparacion2, punteroExpresion.pop());
                    :};

/*
            WHILE
*/

while               ::= WHILE PAREN_OPEN condicion PAREN_CLOSE programa ENDWHILE
                    {:
                    	punteroWhile = new NodoIntermedio("WHILE", punteroCondicion, punteroPrograma.pop());
                         System.out.println("while -> WHILE PAREN_OPEN condicion PAREN_CLOSE programa ENDWHILE");
                    :};
/*
            display
*/
display             ::= DISPLAY expresion
                    {:
                    	punteroDisplay = new NodoIntermedio("DISPLAY", new Hoja("NOP"), punteroExpresion.pop());
                         System.out.println("display -> display expresion");
                    :};
/*
            get
*/
get                ::= GET IDENTIFICADOR:ID
                    {:
                    	if(!this.identifierList.contains(ID)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+"' no ha sido declarado."); 
                    	}
                    	punteroGet = new NodoIntermedio("GET", new Hoja("NOP"), new Hoja(ID));
                         System.out.println("get -> get IDENTIFICADOR");
                    :};
/*
            for
*/
for                ::= FOR asignacion TO expresion {: auxFor = punteroAsignacion; RESULT= new Symbol(-1); :} programa NEXT IDENTIFICADOR:ID
                    {:
                    	if(!this.identifierList.contains(ID)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+"' no ha sido declarado."); 
                    	}
                    	auxTo = new NodoIntermedio("TO", auxFor, punteroExpresion.pop());
                    	punteroFor = new NodoIntermedio("FOR", auxTo, punteroPrograma.pop());
                         System.out.println("for -> for asignacion TO expresion programa NEXT identificador");
                    :}
					| FOR asignacion TO expresion CORCHETE_OPEN CONSTANTE_ENTERA:CTE CORCHETE_CLOSE {: auxFor = punteroAsignacion; RESULT= new Symbol(-1); :} programa NEXT IDENTIFICADOR:ID
                    {:
                    	if(!this.identifierList.contains(ID)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+"' no ha sido declarado."); 
                    	}
                    	auxBy = new NodoIntermedio("BY", auxFor, new Hoja("_"+CTE));
                    	auxTo = new NodoIntermedio("TO", auxBy, punteroExpresion.pop());
                    	punteroFor = new NodoIntermedio("FOR", auxTo, punteroPrograma.pop());
                         System.out.println("for -> FOR asignacion TO expresion CORCHETE_OPEN CONSTANTE_ENTERA CORCHETE_CLOSE programa NEXT identificador");
                    :};
/*
            long
*/
long                ::= LONG PAREN_OPEN CORCHETE_OPEN identificadoresLong CORCHETE_CLOSE PAREN_CLOSE
                    {:
                    	auxTipo = "CTE_INTEGER";
                    	punteroLong = new Hoja("_long_"+Integer.toString(IdsCount));
                         tablaDeSimbolos.add("_long_"+Integer.toString(IdsCount), "CTE_FLOAT", Integer.toString(IdsCount), null);
                         
                         System.out.println("long -> LONG PAREN_OPEN CORCHETE_OPEN identificadoresLong CORCHETE_CLOSE PAREN_CLOSE");
                    :};
                    
identificadoresLong     ::= IDENTIFICADOR:ID
                    {:
                    	if(!this.identifierList.contains(ID)){
                    		throw new Error("Error de sintaxis: El ID '"+ID+"' no ha sido declarado."); 
                    	}
                    	IdsCount = 1;
                    :}
                    | identificadoresLong COMA IDENTIFICADOR:ID
                    {:
                    	IdsCount++;
                    :};
