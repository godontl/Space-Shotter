package it.unibs.eps.spaceshooter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Asteroide extends Thread {
    private int x, y;
    private int width, height;
    private int speed = 2;
    private BufferedImage asteroideImage;
    private Random rand = new Random();

    public Asteroide(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = rand.nextInt(20) + 30;
        this.height = rand.nextInt(20) + 30;

        // Caricamento immagine
        try {
            asteroideImage = ImageIO.read(getClass().getResource("/images/asteroide.png"));
        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare l'immagine dell'asteroide!");
        }
    }

    public void run() {
        y += speed; // L'asteroide scende
    }




    // Metodo per verificare collisioni con l'astronave
    public boolean checkCollision(Astronave astronave) {
        Rectangle asteroideRect = getShape();
        Rectangle astronaveRect = astronave.getShape();
        return asteroideRect.intersects(astronaveRect);  // Verifica se c'è sovrapposizione
    }

    // Metodo per verificare collisioni con proiettili
    public boolean checkCollisionWithProjectile(Proiettile proiettile) {
        Rectangle asteroideRect = getShape();
        Rectangle proiettileRect = proiettile.getShape();
        return asteroideRect.intersects(proiettileRect);
    }

    public Rectangle getShape() {
        return new Rectangle(x, y, width, height);
    }

    // Getters e setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public void paintComponent(Graphics g, Color c) {
        if (asteroideImage != null) {
            g.drawImage(asteroideImage, x, y, width, height, null);
        } else {
            g.setColor(c);
            g.fillOval(x, y, width, height);
        }
    }
}