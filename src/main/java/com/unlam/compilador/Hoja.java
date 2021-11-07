package com.unlam.compilador;

import java.util.ArrayList;
import java.util.List;

import SymbolTable.SymbolTable;

public class Hoja implements Nodo {
	
	private String val;
	private String result;
	
	public Hoja(String val) {
		this.val = val;
		this.result = val;
	}
	
	public String getString(String pre) {
		return pre + val +"\n";
	}
	
	public String getVal() {
		return this.val;
	}
	
	public String getResult() {
		return this.result;
	}

	@Override
	public List<String> Execute(SymbolTable tablaSimbolos) throws Exception {
		List<String> res = new ArrayList<String>();
		return res;
	}
}