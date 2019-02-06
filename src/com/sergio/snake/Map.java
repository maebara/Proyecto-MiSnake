package com.sergio.snake;

import java.awt.Point;
import java.util.Arrays;

public class Map {

	private int[][] map;
	private int n;
	private Point fruta;

	public static final int FRUTA = 2;

	public Map(int n, Point fruta) { // n = grado matriz
		map = new int[n][n];
		this.n = n;
		this.fruta = fruta;
		map[fruta.y][fruta.x] = Map.FRUTA;
	}

	public Map(int n) { // n = grado matriz
		map = new int[n][n];
		this.n = n;
		this.map[7][7] = Map.FRUTA;
	}
	
	public int getValueAt(Point head) {
		return map[head.y][head.x];
	}

	public int getN() {
		return this.n;
	}

	public int getValueAt(int x, int y) {
		return map[y][x];
	}

	public void setPos(Point p, int val) {
		map[p.y][p.x] = val;
	}

	public int[][] getMap() {
		return this.map;
	}

	public void mostrarMapa() {
		for (int i = 0; i < map.length; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	public Point generarFruta() {

		int x = (int) (Math.random() * n);
		int y = (int) (Math.random() * n);

		while (this.getValueAt(x, y) != 0) {
			x = (int) (Math.random() * n);
			y = (int) (Math.random() * n);
		}
		
		this.map[y][x] = Map.FRUTA;

		this.fruta = new Point(x, y);
		return fruta;
	}

	public Point getFruta() {
		return this.fruta;
	}
}
