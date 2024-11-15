package it.unibs.eps.spaceshooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Astronave extends JPanel{

	public static final int ALTEZZA = 15;
	public static final int LUNGHEZZA = 30;
	private int x = 100;
	private int y = 300;
	
	public int velocitaX = 0;
	public int velocitaY = 0;
	
	public Rectangle getShape() {
		return new Rectangle(x, y, LUNGHEZZA, ALTEZZA);
	}
	
	public void paintComponent(Graphics g, Color c) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(c);
		g2.draw(getShape());
	}
	
	public int getXMedia() {
		return x + LUNGHEZZA / 2;
	}
	
	public void setVelocitaX(int velocitaX) {
	
		this.velocitaX = velocitaX;
	}
	
	
	public void setVelocitaY(int velocitaY) {
		this.velocitaY = velocitaY;
	}

	public void move(Rectangle bordi) {
		if(!(x + velocitaX <= bordi.getMinX() && velocitaX < 0 || x + LUNGHEZZA + velocitaX >= bordi.getMaxX() && velocitaX > 0)) {
			x += velocitaX;
		}
		
		if(!(y + velocitaY <= bordi.getMinY() && velocitaY < 0 || y + ALTEZZA + velocitaY >= bordi.getMaxY() && velocitaY > 0)) {
			y += velocitaY;
		}
	}
}
