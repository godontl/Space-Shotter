package it.unibs.eps.spaceshooter;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Proiettile extends Thread{

    private static final int LUNGHEZZA = 5;
    private static final int ALTEZZA = 10;
    private static final int VELOCITA = 2;

    private int x;
    private int y;
    private int tempo = 0;
    private BufferedImage proiettileImage;

    public Proiettile(int x, int y) {
        this.x = x-2;
        this.y = y-10;

        try {
            proiettileImage = ImageIO.read(getClass().getResource("/images/proiettile.png"));
        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare l'immagine del proiettile!");
        }
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
        if (proiettileImage != null) {
            g.drawImage(proiettileImage, x, y, LUNGHEZZA, ALTEZZA, null);
        } else {
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(c);
            g2.fill(getShape());
        }
    }

    public void run() {
        y = y - VELOCITA;
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