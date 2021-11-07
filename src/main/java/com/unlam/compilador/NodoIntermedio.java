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

	public void GenerarAsembler(SymbolTable tablaSimbolos) throws Exception{
		List<String> res = new LinkedList<String>();
		
		res.add(generateHeaders(tablaSimbolos));
		
		res.addAll(this.Execute(tablaSimbolos));
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter("assembler.txt"))) {
        	
			for (String string : res) {
	        	br.write(string + "\n");
			}
			
        } catch (Exception e) {
            e.printStackTrace();
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
				res.add("MOV R1," + this.der.getResult());
				res.add("MOV " + this.izq.getResult() +", R1");
				return res;
			case "OP_PLUS":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add("MOV R1," + this.izq.getResult());
				res.add("ADD R1, " + this.der.getResult());
				aux = getAux();
				this.result = aux;
				res.add("MOV "+aux+", R1");
				return res;
			case "OP_MINUS":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add("MOV R1," + this.izq.getResult());
				res.add("SUB R1, " + this.der.getResult());
				aux = getAux();
				this.result = aux;
				res.add("MOV "+aux+", R1");
				return res;
			case "OP_MULTI":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add("MOV R1," + this.izq.getResult());
				res.add("MUL R1, " + this.der.getResult());
				aux = getAux();
				this.result = aux;
				res.add("MOV "+aux+", R1");
				return res;
			case "OP_DIVISION":
				res = new LinkedList<String>();
				res.addAll(this.izq.Execute(tablaSimbolos));
				res.addAll(this.der.Execute(tablaSimbolos));
				res.add("MOV R1," + this.izq.getResult());
				res.add("DIV R1, " + this.der.getResult());
				aux = getAux();
				this.result = aux;
				res.add("MOV "+aux+", R1");
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
