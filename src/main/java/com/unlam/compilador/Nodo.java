package com.unlam.compilador;

import java.util.List;

import SymbolTable.SymbolTable;

public interface Nodo {

	public List<String> Execute(SymbolTable tablaSimbolos) throws Exception;
	public String getString(String pre);
	public String getVal();
	public String getResult();
}
