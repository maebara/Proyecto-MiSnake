package com.sergio.bot;

public class BfsSolucion {
	private int[] precedencias;
	private int[] distancias;
	
	public BfsSolucion(int[] precedencias, int[] distandias) {
		this.precedencias = precedencias;
		this.distancias = distandias;
	}
	
	public int[] getPrecedencias() {
		return precedencias;
	}
	public void setPrecedencias(int[] precedencias) {
		this.precedencias = precedencias;
	}
	public int[] getDistandias() {
		return distancias;
	}
	public void setDistandias(int[] distandias) {
		this.distancias = distandias;
	}
}
