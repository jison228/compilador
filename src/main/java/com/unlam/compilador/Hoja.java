package com.unlam.compilador;

import java.util.ArrayList;
import java.util.List;

public class Hoja implements Nodo {
	
	private String val;
	
	public Hoja(String val) {
		this.val = val;
	}
	
	public String getString(String pre) {
		return pre + val +"\n";
	}
	
	public String getVal() {
		return this.val;
	}

	@Override
	public List<String> Execute() throws Exception {
		List res = new ArrayList<String>();
		res.add(val);
		return res;
	}
}