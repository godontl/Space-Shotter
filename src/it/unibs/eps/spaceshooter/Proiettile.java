package it.unibs.eps.spaceshooter;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Proiettile extends Thread{

    private static final int LUNGHEZZA = 5;
    private static final int ALTEZZA = 10;
    private static final int VELOCITA = 1;

    private int x;
    private int y;
    private int tempo = 0;

    public Proiettile(int x, int y) {
        this.x = x-2;
        this.y = y-10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getShape() {
        return new Rectangle(x, y, LUNGHEZZA, ALTEZZA);
    }

    public void paintComponent(Graphics g, Color c) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(c);
        g2.fill(getShape());
    }

    public void run() {
        y = y - VELOCITA;
//    	while(true) {
//	    	 Timer timer = new Timer(1, new ActionListener() {
//	             @Override
//	             public void actionPerformed(ActionEvent e) {
//	            	 if(tempo%10 == 0) {
//	            		 y = y - VELOCITA;
//	            	 }
//	            	 try {
//	            		 sleep(1000);
//	            	 }
//	            	 catch (InterruptedException errore) {
//						// TODO: handle exception
//					}
//	             }
//	    	 });
//    	}
    }


    public int getWidth() {
        // TODO Auto-generated method stub
        return LUNGHEZZA;
    }

    public int getHeight() {
        // TODO Auto-generated method stub
        return ALTEZZA;
    }



}