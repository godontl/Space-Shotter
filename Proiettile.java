package it.unibs.eps.spaceshooter;

import java.awt.Rectangle;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Proiettile extends JPanel{
	
	private static final int LUNGHEZZA = 5;
	private static final int ALTEZZA = 10;
	private static final int VELOCITA = 1;
	
	private int x;
	private int y;
	
	public Proiettile(int x, int y) {
		this.x = x-2;
		this.y = y-10;
	}
	
	public Rectangle getShape() {
		return new Rectangle(x, y, LUNGHEZZA, ALTEZZA);
	}
	
	public void paintComponent(Graphics g, Color c) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(c);
		g2.fill(getShape());
	}
	
	public void moveUp() {
		y = y - VELOCITA;
	}
	
	

}

