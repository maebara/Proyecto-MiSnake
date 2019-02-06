package com.sergio.bot;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Stack;

import com.sergio.snake.Map;

public class SnakeIA {
	public static final int ARRIBA = 0;
	public static final int ABAJO = 9;
	public static final int IZQUIERDA = 8;
	public static final int DERECHA = 1;
	private LinkedList<Point> body;
	private int dir;
	private Map map;
	private int id;
	private int deltaX;
	private int deltaY;
	private LinkedList<Integer> direcciones;
	private Grafo grafo;

	public SnakeIA(Map map, int id, Point posInicial) {
		body = new LinkedList<>();
		body.add(posInicial);
		map.setPos(posInicial, id);
		this.map = map;
		this.id = id;
		dir = 1;
		deltaX = 1;
		deltaY = 0;
		grafo = new Grafo(map.getN());
		direcciones = new LinkedList<>();
		// this.cargarListaDirecciones(posInicial, map.generarFruta());
		cargarListaDirecciones(posInicial, new Point(7, 7));
	}

	public void move() {

		int nextDir;
		if (direcciones.size() != 0) {
			nextDir = direcciones.removeFirst();
			this.cambiarDir(nextDir);
		}

		Point head = body.get(0);
		Point newPos = new Point((int) head.x + deltaX, (int) head.y + deltaY);

		body.addFirst(newPos);

		if (hayFruta(map, newPos)) {
			// Point cola = body.removeLast();
			map.setPos(newPos, id);
			// map.setPos(cola, 0);

			Point fruta = map.generarFruta();
			this.cargarListaDirecciones(newPos, fruta);

		} else {
			Point cola = body.removeLast();
			map.setPos(newPos, id);
			map.setPos(cola, 0);
		}
	}

	private void cargarListaDirecciones(Point head, Point fruta) {

		grafo = new Grafo(map.getN() * map.getN());
		int n = map.getN();
		int nodo;
		int nodoDerecha;
		int nodoAbajo;

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {

				if (caminoValido(x, y, head)) {
					nodo = x + y * n;
					if (x != n - 1 && caminoValido(x + 1, y, head)) { // derecha
						nodoDerecha = nodo + 1;
						grafo.agregarRelacion(nodo, nodoDerecha);
					}
					if (y != n - 1 && caminoValido(x, y + 1, head)) { // abajo
						nodoAbajo = nodo + n;
						grafo.agregarRelacion(nodo, nodoAbajo);
					}
				}

			}
		}

		if (this.body.size() == 1) {
			quitarRelacionDetras(head, n);
		}

		BfsSolucion solucion = grafo.bfs(head.x + head.y * n);
		Stack<Point> sig = new Stack<>();
		int[] precedencias = solucion.getPrecedencias();
		int frutaPos = fruta.x + fruta.y * n;
		sig.push(fruta);
		int padre = precedencias[frutaPos];
		sig.push(new Point(padre % n, padre / n));
		while (precedencias[padre] != padre) {
			padre = precedencias[padre];
			sig.push(new Point(padre % n, padre / n));
		}

		Point inicio = sig.pop();
		Point siguiente = null;

		while (!sig.isEmpty()) {
			siguiente = sig.pop();

			if (inicio.x == siguiente.x) {
				if (inicio.y < siguiente.y) {
					direcciones.addLast(ABAJO);
				} else {
					direcciones.addLast(ARRIBA);
				}
			}

			if (inicio.y == siguiente.y) {
				if (inicio.x < siguiente.x) {
					direcciones.addLast(DERECHA);
				} else {
					direcciones.addLast(IZQUIERDA);
				}
			}
			inicio = siguiente;
		}
/*
		System.out.println("lista bfs");
		direcciones.forEach(x -> {
			
			switch (x) {
			case 0:
				System.out.println("Arriba");
				break;

			case 9:
				System.out.println("Abajo");
				break;

			case 8:
				System.out.println("Izquierda");
				break;

			case 1:
				System.out.println("Derecha");
				break;
			}
		});
	*/
	}

	private void quitarRelacionDetras(Point head, int n) {

		int nodo = head.x + head.y * n;
		int nodoVecino = -1;

		switch (dir) {
		case ARRIBA:
			nodoVecino = nodo + n;
			break;

		case ABAJO:
			nodoVecino = nodo - n;
			break;

		case IZQUIERDA:
			nodoVecino = nodo + 1;
			break;

		case DERECHA:
			nodoVecino = nodo - 1;
			break;
		}

		if (nodo < 0 || nodoVecino < 0 || nodo >= n * n || nodoVecino >= n * n)
			return;

		grafo.quitarRelacion(nodo, nodoVecino);
	}

	private boolean caminoValido(int x, int y, Point head) {

		int val = map.getValueAt(x, y);

		if (val == this.id) {
			boolean valido = head.x != x || head.y != y;
			return !valido;
		}

		return true;
	}

	private boolean hayFruta(Map map, Point head) {
		return map.getValueAt(head) == Map.FRUTA;
	}

	public void cambiarDir(int dir) {
		if (this.dir + dir == 9)
			return;

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
