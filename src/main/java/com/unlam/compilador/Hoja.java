package com.unlam.compilador;

public class Hoja implements Nodo {
	
	private String val;
	
	public Hoja(String val) {
		this.val = val;
	}
	
	public void Execute() {
		return;
	}
	
	public String getString(String pre) {
		return pre + val +"\n";
	}
	
	public String getVal() {
		return this.val;
	}
}