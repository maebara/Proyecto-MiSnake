package com.sergio.graficos;

import java.awt.Color;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Point;

public class JVentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanelGrafico panelGrafico;
	private JPanel panelPuntajes;

	public JVentanaJuego(int mapa[][]) {
		setResizable(false);
		this.panelPuntajes = new JPanel();
		panelPuntajes.setBounds(Cuadrado.LADO * mapa.length + 20, 11, 150, Cuadrado.LADO * mapa.length );
		getContentPane().add(panelPuntajes);
		this.panelPuntajes.setBackground(new Color(51, 55, 64));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Cuadrado.LADO * mapa.length + 40 + this.panelPuntajes.getWidth(),
				Cuadrado.LADO * mapa.length + 50);
		this.getContentPane().setBackground(new Color(140, 0, 0));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.dibujarMapa(mapa);
		this.panelPuntajes.setLayout(new BoxLayout(this.panelPuntajes, BoxLayout.PAGE_AXIS));
		this.panelPuntajes.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 15));
		this.setFocusable(true);
	}

	public void actualizarMapa(int[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				c.add(new Cuadrado(new Point(j, i), mapa[i][j]));

			}
		}

		panelGrafico.setCuadrados(c);
		panelGrafico.repaint();
	}

	public void dibujarMapa(int[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				c.add(new Cuadrado(new Point(j, i), mapa[i][j]));

			}
		}

		this.panelGrafico = new JPanelGrafico(c);
		panelGrafico.setBounds(10, 11, Cuadrado.LADO * mapa.length, Cuadrado.LADO * mapa.length);
		getContentPane().add(panelGrafico);

	}

	
	
	
}