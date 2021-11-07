package com.unlam.compilador;

import java.util.List;

public interface Nodo {

	public List<String> Execute() throws Exception;
	public String getString(String pre);
	public String getVal();
}
