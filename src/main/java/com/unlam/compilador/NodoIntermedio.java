package com.unlam.compilador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import SymbolTable.SymbolTable;

public class NodoIntermedio implements Nodo {
	
	private Nodo izq;
	private Nodo der;
	private String val;
	
	public NodoIntermedio(String val,Nodo izq, Nodo der) {
		this.val=val;
		this.izq = izq;
		this.der = der;
	}
	
	public void Execute() {
		return;
	}
	
	public void save() {
	    Logger LOGGER = Logger.getLogger(SymbolTable.class.getName());
	    
        try (BufferedWriter br = new BufferedWriter(new FileWriter("intermedio.txt"))) {

        	br.write(this.getString(""));

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
