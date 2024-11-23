package it.unibs.eps.spaceshooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Astronave {
    public static final int ALTEZZA = 56;
    public static final int LUNGHEZZA = 25;
    private int x = 180;
    private int y = 650;

    private int velocitaX;
    private int velocitaY;

    private BufferedImage img;

    public Astronave() {
        try {
            img = ImageIO.read(getClass().getResource("/images/astronave.png"));
        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare l'immagine dell'astronave!");
        }
    }

    public Rectangle getShape() {
        return new Rectangle(x, y, LUNGHEZZA, ALTEZZA);
    }

    public int getXMedia() {
        return x + LUNGHEZZA / 2;
    }

    public int getY() {
        return y;
    }

    public void setVelocitaX(int velocitaX) {
        this.velocitaX = velocitaX;
    }

    public void setVelocitaY(int velocitaY) {
        this.velocitaY = velocitaY;
    }

    public void move(Rectangle bordi) {
        // Movimento orizzontale
        if (!(x + velocitaX <= bordi.getMinX() && velocitaX < 0 ||
                x + LUNGHEZZA + velocitaX >= bordi.getMaxX() && velocitaX > 0)) {
            x += velocitaX;
        }

        // Movimento verticale
        if (!(y + velocitaY <= bordi.getMinY() && velocitaY < 0 ||
                y + ALTEZZA + velocitaY >= bordi.getMaxY() && velocitaY > 0)) {
            y += velocitaY;
        }
    }


    public void paintComponent(Graphics g, Color c) {
        if (img != null) {
            g.drawImage(img, x, y, LUNGHEZZA, ALTEZZA, null);
        } else {
            g.setColor(c);
            g.fillRect(x, y, LUNGHEZZA, ALTEZZA);
        }
    }

    public int getWidth() {
        return Astronave.LUNGHEZZA;  // Usa la lunghezza dell'astronave
    }

    public int getHeight() {
        return Astronave.ALTEZZA;  // Usa l'altezza dell'astronave
    }

}
