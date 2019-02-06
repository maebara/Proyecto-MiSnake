package com.sergio.bot;

import java.util.LinkedList;
import java.util.List;

public class ListaAdj {
	private List<List<Integer>> relaciones;
	
	public ListaAdj(int nodos) {
		relaciones = new LinkedList<List<Integer>>();
		
		for(int i = 0; i < nodos ; i++) {
			relaciones.add(new LinkedList<>());
		}	
	}

	public List<Integer> obtenerRelaciones(int nodo){
		return relaciones.get(nodo);
	}
	
	public void agregarRelacion(int nodo1, int nodo2) {
		relaciones.get(nodo1).add(nodo2);
		relaciones.get(nodo2).add(nodo1);
	}
	
	public void quitarRelacion(Integer nodo1, Integer nodo2) {
		relaciones.get(nodo1).remove(nodo2);
		relaciones.get(nodo2).remove(nodo1);
	}
	
	public void mostrarLista() {
		String nodos = "";
		int i = 0;
		for (List<Integer> lista : this.relaciones) {
			nodos = i + ": ";
			for (Integer nodo : lista) {
				nodos += nodo + ", ";
			}
			System.out.println(nodos);
			nodos = "";
			i++;
		}
	}
}
