package it.unibs.eps.spaceshooter;

import java.awt.*;
import java.util.Random;

public class Asteroide {
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

    public void moveDown() {
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


