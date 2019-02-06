package com.sergio.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import javax.swing.JPanel;

public class JPanelGrafico extends JPanel {
	private static final long serialVersionUID = 1L;
	private Collection<Cuadrado> cuadrados;

	public JPanelGrafico(Collection<Cuadrado> cuadrados) {
		this.cuadrados = cuadrados;
	}

	public void paintComponent(Graphics g) {
		this.setBackground(Color.WHITE);

		for (Cuadrado c : this.cuadrados) {

			if (c.esFruta()) {
				g.setColor(Color.RED);
				g.fillRect(c.getX() * Cuadrado.LADO, c.getY() * Cuadrado.LADO, Cuadrado.LADO, Cuadrado.LADO);
		
			} else if (c.esJugador()) {
				g.setColor(Color.GREEN);
				g.fillRect(c.getX() * Cuadrado.LADO, c.getY() * Cuadrado.LADO, Cuadrado.LADO, Cuadrado.LADO);

			} else {
				g.setColor(Color.WHITE);
				g.fillRect(c.getX() * Cuadrado.LADO, c.getY() * Cuadrado.LADO, Cuadrado.LADO, Cuadrado.LADO);
			}

		}

	}

	public void setCuadrados(Collection<Cuadrado> cuadrados) {
		this.cuadrados = cuadrados;
	}

}