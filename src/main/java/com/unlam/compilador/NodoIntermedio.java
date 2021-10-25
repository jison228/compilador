package com.unlam.compilador;

public class NodoIntermedio implements Nodo {
	
	private Nodo izq;
	private Nodo der;
	private String val;
	
	public NodoIntermedio(String val,Nodo izq, Nodo der) {
		this.izq = izq;
		this.der = der;
	}
	
	public void Execute() {
		return;
	}
}
