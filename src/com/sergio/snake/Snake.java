package com.sergio.snake;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Point> body;
	public static final int ARRIBA = 0;
	public static final int ABAJO = 9;
	public static final int IZQUIERDA = 8;
	public static final int DERECHA = 1;
	private int dir;
	private Map map;
	private int id;
	private int deltaX;
	private int deltaY;
	
	public Snake(Map map, int id) {
		body = new LinkedList<>();
		body.add(new Point(8,8));
		this.map = map;
		this.id = id;
		dir = 1;
		deltaX = 1;
		deltaY = 0;
	}

	public void move() {
		Point head = body.get(0);
		Point newPos = new Point((int) head.x + deltaX, (int) head.y + deltaY);
		body.addFirst(newPos);
		body.removeLast();
	
		if(hayFruta(map, newPos)) {
			map.generarFruta();
		}
		
		map.setPos(newPos, id);
		map.setPos(head, 0);
	
	}

	private boolean hayFruta(Map map, Point head) {
		return map.getValueAt(head) == Map.FRUTA;
	}	

	public void cambiarDir(int dir) {
		if (this.dir + dir == 9) return;
		
		this.dir = dir;
		switch (dir) { // horrible switch :S
		case 0:
			deltaX = 0;
			deltaY = -1;
			break;

		case 8:
			deltaX = -1;
			deltaY = 0;
			break;

		case 1:
			deltaX = 1;
			deltaY = 0;
			break;

		case 9:
			deltaX = 0;
			deltaY = 1;
			break;

		}
	}



}
