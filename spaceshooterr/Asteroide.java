package it.unibs.fp.spaceshooterr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Asteroide extends Thread{
	
	    private int x, y;
	    private int width, height;
	    private int speed = 2;
	    private Random rand = new Random();

	    public Asteroide(int x, int y) {
	        this.x = x;
	        this.y = y;
	        this.width = rand.nextInt(20) + 30;
	        this.height = rand.nextInt(20) + 30;
	    }

	    public void paintComponent(Graphics g, Color c) {
	        g.setColor(c);
	        g.fillOval(x, y, width, height); // Disegna l'asteroide
	    }

	    public void run() {
	        y += speed;  // L'asteroide scende
	    }

	    public boolean checkCollision(Astronave astronave) {
	        Rectangle asteroideRect = new Rectangle(x, y, width, height);
	        Rectangle astronaveRect = new Rectangle(astronave.getX(), astronave.getY(), astronave.getWidth(), astronave.getHeight());
	        return asteroideRect.intersects(astronaveRect);  // Collisione con l'astronave
	    }

	    public boolean checkCollisionWithProjectile(Proiettile proiettile) {
	        Rectangle asteroideRect = new Rectangle(x, y, width, height);
	        Rectangle proiettileRect = new Rectangle(proiettile.getX(), proiettile.getY(), proiettile.getWidth(), proiettile.getHeight());
	        return asteroideRect.intersects(proiettileRect);  // Collisione con il proiettile
	    }

	    public int getX() { return x; }
	    public int getY() { return y; }
	    public int getWidth() { return width; }
	    public int getHeight() { return height; }

	    public void setSpeed(int newSpeed) {
	        this.speed = newSpeed;
	    
	}	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	private static final int LUNGHEZZA = 5;
//	private static final int ALTEZZA = 10;
//	private static final int VELOCITA = 2;
//	
//	private int x;
//	private int y;
//	
//	private BufferedImage img;
//	
//	public Asteroide(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//	
//	public Rectangle getShape() {
//		return new Rectangle(x, y, LUNGHEZZA, ALTEZZA);
//	}
//	
//	public void paintComponent(Graphics g, Color c) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D)g;
//		g2.setColor(c);
//		g2.fill(getShape());
//	}
//	
//	public void move() {
//		y = y + VELOCITA;
//	}
//	
//	 public void paintImage(Graphics g) {
//    	 try {
//       	
//       	URL url = new URL("https://th.bing.com/th/id/OIP.FUt0lq7xZDgngJ2zoh8lFAHaL0?w=802&h=1280&rs=1&pid=ImgDetMain"); 
//       	img = ImageIO.read(url);
//
//       }
//       catch (IOException e) {
// 		 System.out.println("Errore");
// 		}   
//        Graphics2D g2 = (Graphics2D)g;
//        g2.drawImage(img, x, y, LUNGHEZZA, ALTEZZA, null);
//    }
//    
//    public void removeImage(Graphics g) {
//       Graphics2D g2 = (Graphics2D)g;
//       Rectangle r = new Rectangle(x, y, LUNGHEZZA + 1, ALTEZZA + 1);
//       g2.setColor(Color.black);
//       g2.fill(r);
//   }

//}
