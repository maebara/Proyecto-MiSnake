package com.sergio.test;

import java.awt.Point;

import com.sergio.bot.SnakeIA;
import com.sergio.graficos.JVentanaJuego;
import com.sergio.snake.Map;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		Map mapa = new Map(30);
		SnakeIA vibora = new SnakeIA(mapa, 1, new Point(5,7));
		JVentanaJuego jv = new JVentanaJuego(mapa.getMap());
		jv.setVisible(true);

		for(int i = 0; i < 100000000; i++) { //tiempo
			vibora.move();	
			jv.actualizarMapa(mapa.getMap());
			Thread.sleep(40);	
		}
		
	}
}
