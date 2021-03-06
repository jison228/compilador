package SymbolTable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.logging.Logger;

public class SymbolTable {

    public static Logger LOGGER = Logger.getLogger(SymbolTable.class.getName());

    private List<Simbolo> symbolList;

    public SymbolTable() {
        this.symbolList = new LinkedList<Simbolo>();
        this.add("__0", "CTE_INTEGER", "0", null);
        this.add("__1", "CTE_INTEGER", "1", null);
    }

    // Getters & Setters
    public List<Simbolo> getLista(){
        return symbolList;
    }

    // Public methods

    public void add(String nombre, String tipo, String valor, Integer longitud) {
        if(!isInTable(nombre)){
            symbolList.add(new Simbolo(nombre, tipo, valor, longitud));
        }
    }

    // Es decir, cada vez que vas encontrando identificadores, los guardás en una cola/lista. Cuando encontrás un tipo, vacías esa lista, y le agregás 
    // a esos identificadores el tipo encontrado. 
    // Esto es porque nuestro lenguaje permite declaraciones de este tipo:
    // a, b, c : FLOAT;
    // Si hay un identificador en la cola y nunca se le asignó su tipo, bueno, hay un problema. De todas formas no debería pasar, porque la regla sintáctica
    // debería exigir que las declaraciones sean "ID: TIPO"
    public void addIdentifiers(ArrayList<String> identifiers, String dataType) {
    	Iterator<String> i = identifiers.iterator();
    	while (i.hasNext()) {
           // must be called before you can call i.remove()
    	   String id = i.next(); 
           if (!(isInTable(id)))
           {
    	        this.add(id, dataType, "-", null);
           }
           else {
               throw new Error("Error de sintaxis: la variable '"+id+"' ya habia sido declarada."); 
           }
           // Remove identifier from list
    	   i.remove();
    	}    	
    }
    

    public void addIdentifiers(ArrayList<String> identifiers, ArrayList<String> dataType) {
    	Iterator<String> i = identifiers.iterator();
    	Iterator<String> d = dataType.iterator();
    	while (i.hasNext()) {
           // must be called before you can call i.remove()
    	   String id = i.next(); 
           if (!(isInTable(id)))
           {
    	        this.add(id, d.next(), "-", null);
           }
           else {
               throw new Error("Error de sintaxis: la variable '"+id+"' ya habia sido declarada."); 
           }
           // Remove identifier from list
    	   i.remove();
    	   d.remove();
    	}    	
    }
    

    public String getTipo(String identifier) {
    	
    	if(identifier.substring(0, 1).equals("@")) {
    		return "CTE_FLOAT";
    	}
    	
    	return this.symbolList.stream().filter(i -> i.getNombre().equals(identifier)).findFirst().get().getTipo();
    }

    public Boolean isInTable(String nombre) {
        return symbolList.stream().anyMatch(simbolo -> simbolo.getNombre().equals(nombre));
    }

    public void save() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("ts.txt"))) {

            br.write(String.format("%-30s|%-30s|%-30s|%-30s\n", "NOMBRE", "TIPODATO", "VALOR", "LONGITUD"));
            symbolList.forEach(simbolo -> {
                try {
                    br.write(simbolo.toString() + "\n");
                } catch (IOException e) {
                    LOGGER.severe("Ocurrio un error al guardar los simbolos");
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            LOGGER.severe("Ocurrio un error al guardar el archivo");
            e.printStackTrace();
        }
    }
}