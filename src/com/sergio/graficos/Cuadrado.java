package com.sergio.graficos;

import java.awt.Point;
import com.sergio.snake.Map;

public class Cuadrado {
	public static final int LADO = 20;
	private Point pos;
	private int codElemento;
	
	public Cuadrado(Point pos, int codElemento) {
		this.pos = pos;
		this.codElemento = codElemento;		
	}
	
	public int getX() {
		return pos.x;
	}
	
	public int getY() {
		return pos.y;
	}
	
	public boolean esFruta() {
		return this.codElemento == Map.FRUTA;
	}
	
	public boolean esJugador() {
		return this.codElemento == 1;
	}
	
	public int getcodElemento() {
		return this.codElemento;
	}

}
