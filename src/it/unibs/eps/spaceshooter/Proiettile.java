package it.unibs.eps.spaceshooter;

import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Proiettile extends Thread{

    private static final int LUNGHEZZA = 20;
    private static final int ALTEZZA = 20;
    private static final int VELOCITA = 2;

    private int x;
    private int y;
    private int distance = 0;
    private BufferedImage proiettileImage;

    public Proiettile(int x, int y) {
        this.x = x-LUNGHEZZA/2;
        this.y = y-ALTEZZA;

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
        distance += VELOCITA;
    }

    public int getDistanceTraveled(){
        return distance;
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