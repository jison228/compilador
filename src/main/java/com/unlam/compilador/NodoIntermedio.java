package com.unlam.compilador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import SymbolTable.Simbolo;
import SymbolTable.SymbolTable;

public class NodoIntermedio implements Nodo {
	
	private Nodo izq;
	private Nodo der;
	private String val;
	
	private String result;
	private static int lastAux = 0;
	
	private static List<String> cachoDeCodigoMagicoDeFor = null;
	private static String cachoDeCodigoMagicoDeFor_BY = null;
	
	public NodoIntermedio(String val,Nodo izq, Nodo der) {
		this.val=val;
		this.izq = izq;
		this.der = der;
	}
	
	private String getAux() {
		String res ="@aux" + lastAux;
		lastAux++;
		return res;
	}
	
	private String getAuxInt(SymbolTable st) {
		String res ="@aux" + lastAux;
		lastAux++;
		st.add(res,  "CTE_FLOAT", "-", null);
		return res;
	}
	
	public String getVal() {
		return this.val;
	}
	
	public String getResult() {
		return this.result;
	}
	
	private String generateData(SymbolTable tablaSimbolos) {		
		String data = "";
		
		for (Simbolo sim : tablaSimbolos.getLista()) {
			String value = "?";
			String simbolValue = sim.getValor();
			
			if (simbolValue != "-")
			{
				Integer length = sim.getLongitud();
				if (sim.getLongitud() != null)
				{
					value = "\"" + simbolValue +"\"";
					data += String.format("\t%s\tdb\t%s,'$',%d dup(?)\n", sim.getNombre(), value, length);
				}				
				else {
					value = Double.valueOf(simbolValue).toString();
					data += String.format("\t%s\tdd\t%s\n", sim.getNombre(), value);
				}
			}
			else
			{
				data += String.format("\t%s\tdd\t%s\n", sim.getNombre(), value);
			}		
			
		}
		
		return data;   
	}
	
	private String generateHeaders(SymbolTable tablaSimbolos) {
        String includes = "include number.asm\ninclude macros2.asm\n\n.MODEL LARGE ;Modelo de Memoria\n .386 ;Tipo de Procesador\n.STACK 200h ;Bytes en el Stack\n\n";
        String data = ".DATA\n %s";
        String dataVariables = generateData(tablaSimbolos);
        return includes + String.format(data, dataVariables);   
	}
	
	private String generateFooters() {
        return "MOV EAX, 4C00h\n" +
        "INT 21h\n\n" +
        "END start";
	}

	public void GenerarAsembler(SymbolTable tablaSimbolos) throws Exception{
		List<String> res = new LinkedList<String>();
		
		List<String> auxCode = this.Execute(tablaSimbolos);
		
		res.add(generateHeaders(tablaSimbolos));
		res.add(".CODE\n\n" + "start:\n" + "MOV EAX,@DATA\n" + "MOV DS,EAX\n" + "MOV ES,EAX\n\n" );

		res.addAll(auxCode);

		res.add(generateFooters());
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter("assembler.txt"))) {
        	
			for (String string : res) {
	        	br.write(string + "\n");
			}
			
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void cargar(SymbolTable st,List<String> res,String val) {
		if(st.getTipo(val).equals("CTE_INTEGER")) {
			res.add("FILD " + val);
		}else {
			res.add("FLD " + val);
		}
	}
	
	private void desCargar(SymbolTable st,List<String> res,String val) {
		if(st.getTipo(val).equals("CTE_INTEGER")) {
			res.add("FISTP " + val);
		}else {
			res.add("FSTP " + val);
		}
	}
	
	public List<String> Execute(SymbolTable tablaSimbolos) throws Exception {
		List<String> res;
		String aux;
		switch(this.val) {
			case "SIG":
				res = this.izq.Execute(tablaSimbolos);
				res.addAll(this.der.Execute(tablaSimbolos));
				return res;
			case "OP_ASIG":
				res = new LinkedList<String>();
				res.addAll(this.der.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.der.getResult());
				this.desCargar(tablaSimbolos, res, this.izq.getResult());
				this.result = this.izq.getResult();
				return res;
			case "OP_PLUS":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.der.getResult());
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				res.add("FADD");
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.desCargar(tablaSimbolos, res, aux);
				res.add("FFREE");
				return res;
			case "OP_MINUS":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				this.cargar(tablaSimbolos, res, this.der.getResult());
				res.add("FSUB");
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.desCargar(tablaSimbolos, res, aux);
				res.add("FFREE");
				return res;
			case "OP_MULTI":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.der.getResult());
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				res.add("FMUL");
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.desCargar(tablaSimbolos, res, aux);
				res.add("FFREE");
				return res;
			case "OP_DIVISION":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				this.cargar(tablaSimbolos, res, this.der.getResult());
				res.add("FDIV");
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.desCargar(tablaSimbolos, res, aux);
				res.add("FFREE");
				return res;
			//ejecutar izq, get result, jump if, ejecutar derecha, poner etiqueta para el jump
			case "IF":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				res.add("FCOMP __1");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				//res.add("CMP 1," + this.izq.getResult()); //1 verdadero, 0 falso
				String tag = "_tag_del_if_"+getAux();
				if(this.der.getVal() != "True/False" ) 
					res.add("JZ " + tag); 
				res.addAll(this.der.Execute(tablaSimbolos));
				if(this.der.getVal() != "True/False" ) 
					res.add(tag +":");
				return res;
			case "True/False":
				res = new LinkedList<String>();
				String tagElse = "_tag_del_else_"+getAux();
				String tagFin = "_tag_del_fin_"+getAux();
				res.add("JZ " + tagElse); 
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.add("JMP " + tagFin); 
				res.add(tagElse +":");
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add(tagFin +":");
				return res;
			case "WHILE":
				res = new LinkedList<String>();
				String tagInicioWhile = "_tag_del_while_"+getAux();
				res.add(tagInicioWhile +":");
				res.addAll(this.izq.Execute(tablaSimbolos));
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				res.add("FCOMP __1");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				String tagContinuaWhile = "_tag_del_while_continua"+getAux();
				res.add("JZ " + tagContinuaWhile); 
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add("JMP " + tagInicioWhile); 
				res.add(tagContinuaWhile +":");
				return res;
			case "FOR":
				res = new LinkedList<String>();
				List<String> auxIzq = this.izq.Execute(tablaSimbolos);
				res.addAll(cachoDeCodigoMagicoDeFor);
				cachoDeCodigoMagicoDeFor = null;
				String tagInicioFor = "_tag_del_for_" + getAux();
				res.add(tagInicioFor +":");
				res.addAll(auxIzq);
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				res.add("FCOMP __1");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				String tagContinuaFor = "_tag_del_for_continua"+getAux();
				res.add("JZ " + tagContinuaFor); 
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add("MOV R1," + this.izq.getResult());
				if(cachoDeCodigoMagicoDeFor_BY == null) {
					res.add("ADD R1, " + "__1");
				}
				else {
					res.add("ADD R1, " + cachoDeCodigoMagicoDeFor_BY);
					cachoDeCodigoMagicoDeFor_BY = null;
				}
				res.add("MOV " + this.izq.getResult() +", R1");
				res.add("JMP " + tagInicioFor); 
				res.add(tagContinuaFor +":");
				return res;
			case "TO":
				LinkedList<String> cachoDeCodigoMagicoDeForAux = new LinkedList<String>();
				cachoDeCodigoMagicoDeForAux.addAll(this.izq.Execute(tablaSimbolos));
				cachoDeCodigoMagicoDeFor = cachoDeCodigoMagicoDeForAux;
				res = new LinkedList<String>();
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				this.cargar(tablaSimbolos, res, this.der.getResult());
				res.add("FCOMP");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				String tagVerdaderoTo = "_tag_de_verdadero_"+getAux();
				String tagFinTo = "_tag_de_continuar_"+getAux();
				res.add("JLE " + tagVerdaderoTo); 
				aux = getAux();
				this.result = aux;
				res.add("MOV " + aux + ", __0");
				res.add("JMP " + tagFinTo); 
				res.add(tagVerdaderoTo +":");
				res.add("MOV " + aux + ", __1");
				res.add(tagFinTo +":");
				return res;
			case "BY":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				this.result = this.izq.getResult();
				cachoDeCodigoMagicoDeFor_BY = this.der.getResult();
				return res;
			case "GET":
				res = new LinkedList<String>();
				res.add("GetFloat " + this.der.getResult());
				return res;
			case "DISPLAY":
				res = new LinkedList<String>();
				if(tablaSimbolos.getTipo(this.der.getResult()).equals("CTE_INTEGER")) {
					res.add("DisplayInteger " + this.der.getResult());
				}else if(tablaSimbolos.getTipo(this.der.getResult()).equals("CTE_STRING")) {
					res.add("DisplayString " + this.der.getResult());
				}else if(tablaSimbolos.getTipo(this.der.getResult()).equals("CTE_FLOAT")) {
					res.add("DisplayFloat " + this.der.getResult() + " , 2");
				}else {
					throw new Exception("Tipo no soportado para Display");
				}
				res.add("newline 1");
				return res;
			case "OP_NOT":
				res = new LinkedList<String>();
				String tagFalsoNOT = "_tag_de_verdadero_"+getAux();
				String tagFinNOT = "_tag_de_continuar_"+getAux();
				res.addAll(this.der.Execute(tablaSimbolos));
				String cmpRes = this.der.getResult();
				res.add("CMP 1," + cmpRes);
				res.add("JZ " + tagFalsoNOT); 
				this.result = cmpRes;
				res.add("MOV " + cmpRes + ", __0");
				res.add("JMP " + tagFinNOT); 
				res.add(tagFalsoNOT +":");
				res.add("MOV " + cmpRes + ", __1");
				res.add(tagFinNOT +":");
				return res;
			case "OP_GTE":
				res = new LinkedList<String>();
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				this.cargar(tablaSimbolos, res, this.der.getResult());
				res.add("FCOMP");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				String tagVerdadero = "_tag_de_verdadero_"+getAux();
				String tagFinGTE = "_tag_de_continuar_"+getAux();
				res.add("JAE " + tagVerdadero); 
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.cargar(tablaSimbolos, res, "__0");
				this.desCargar(tablaSimbolos, res, aux);
				res.add("JMP " + tagFinGTE); 
				res.add(tagVerdadero +":");
				this.cargar(tablaSimbolos, res, "__1");
				this.desCargar(tablaSimbolos, res, aux);
				res.add(tagFinGTE +":");
				return res;
			case "OP_GT":
				res = new LinkedList<String>();
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				this.cargar(tablaSimbolos, res, this.der.getResult());
				res.add("FCOMP");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				String tagVerdadero2 = "_tag_de_verdadero_"+getAux();
				String tagFinGTE2 = "_tag_de_continuar_"+getAux();
				res.add("JA " + tagVerdadero2); 
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.cargar(tablaSimbolos, res, "__0");
				this.desCargar(tablaSimbolos, res, aux);
				res.add("JMP " + tagFinGTE2); 
				res.add(tagVerdadero2 +":");
				this.cargar(tablaSimbolos, res, "__1");
				this.desCargar(tablaSimbolos, res, aux);
				res.add(tagFinGTE2 +":");
				return res;
			case "OP_EQ":
				res = new LinkedList<String>();
				this.cargar(tablaSimbolos, res, this.izq.getResult());
				this.cargar(tablaSimbolos, res, this.der.getResult());
				res.add("FCOMP");
				res.add("FSTSW AX");
				res.add("SAHF");
				res.add("FFREE");
				String tagVerdadero3 = "_tag_de_verdadero_"+getAux();
				String tagFinGTE3 = "_tag_de_continuar_"+getAux();
				res.add("JE " + tagVerdadero3); 
				aux = getAuxInt(tablaSimbolos);
				this.result = aux;
				this.cargar(tablaSimbolos, res, "__0");
				this.desCargar(tablaSimbolos, res, aux);
				res.add("JMP " + tagFinGTE3); 
				res.add(tagVerdadero3 +":");
				this.cargar(tablaSimbolos, res, "__1");
				this.desCargar(tablaSimbolos, res, aux);
				res.add(tagFinGTE3 +":");
				return res;
				
			default:
				throw new Exception("Not implemented case!!: " + this.val);
		}
	}
	
	private String handleArithmeticOperation(NodoIntermedio terceto, String operation)
	{
		String code = "";
		//code += this.loadOperands(terceto, false);
		code += "\t" + operation  + "\n";
		return code;
	}
	
	public void save() {
	    Logger LOGGER = Logger.getLogger(SymbolTable.class.getName());
	    
        try (BufferedWriter br = new BufferedWriter(new FileWriter("intermedia.txt"))) {

        	br.write(this.getString(""));
        	
        	br.write("\n" + java.util.Calendar.getInstance().getTime());

        } catch (Exception e) {
            LOGGER.severe("Ocurrio un error al guardar el archivo");
            e.printStackTrace();
        }
    }
	
	public String getString(String pre) {
		if(izq == null)
			return "ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR IZQ IS NULL";
		if(der == null)
			return "ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR IZQ IS NULL";
		return pre + val +"\n" + izq.getString("   " + pre) + der.getString("   " + pre);
	}
}
