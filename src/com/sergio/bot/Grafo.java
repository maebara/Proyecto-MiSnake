package com.sergio.bot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo {
	public static final int INFINITO = Integer.MAX_VALUE;
	private ListaAdj listaAdj;
	private int cantNodos;
	
	public Grafo(int nodos) {
		cantNodos = nodos;
		listaAdj = new ListaAdj(nodos);
	}
	
	public void agregarRelacion(int nodo1, int nodo2) {
		listaAdj.agregarRelacion(nodo1, nodo2);
	}
	
	public void quitarRelacion(int nodo1, int nodo2) {
		listaAdj.quitarRelacion(nodo1, nodo2);
	}
	
	public BfsSolucion bfs(int nodoInicial) {

		Queue<Integer> cola = new LinkedList<>();
		cola.add(nodoInicial);
		int[] precedencias = new int[cantNodos]; 
		int[] distancias = new int[cantNodos];
		Arrays.fill(distancias, Grafo.INFINITO);
		Arrays.fill(precedencias, -1);
		distancias[nodoInicial] = 0;
		precedencias[nodoInicial] = nodoInicial;
		int nodo = cola.poll();
		
		for (Integer nodoVecino : listaAdj.obtenerRelaciones(nodo)) {
			distancias[nodoVecino] = 1;
			cola.add(nodoVecino);
			precedencias[nodoVecino] = nodo;
		}
		
		while (!cola.isEmpty()) {
			nodo = cola.poll();
			for(Integer nodoVecino : listaAdj.obtenerRelaciones(nodo)) {
				if(distancias[nodoVecino] == Grafo.INFINITO) {
					cola.add(nodoVecino);
					distancias[nodoVecino] = distancias[nodo] + 1;
					precedencias[nodoVecino] = nodo;
				}
				
			}	
		}
		
		return new BfsSolucion(precedencias, distancias);
	}
	
	public void mostrarGrafo() {
		listaAdj.mostrarLista();
	}
}
