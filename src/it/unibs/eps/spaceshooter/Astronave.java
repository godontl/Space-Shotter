package it.unibs.eps.spaceshooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Astronave extends JPanel{

    public static final int ALTEZZA = 15;
    public static final int LUNGHEZZA = 30;
    private int x = 180;
    private int y = 650;

    public int velocitaX;
    public int velocitaY;

    private BufferedImage img;

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
        if(!(x + velocitaX <= bordi.getMinX() && velocitaX < 0 || x + LUNGHEZZA + velocitaX >= bordi.getMaxX() && velocitaX > 0)) {
            x += velocitaX;
        }

        if(!(y + velocitaY <= bordi.getMinY() && velocitaY < 0 || y + ALTEZZA + velocitaY >= bordi.getMaxY() && velocitaY > 0)) {
            y += velocitaY;
        }
    }

    public void paintImage(Graphics g) {
        try {

            URL url = new URL("https://th.bing.com/th/id/OIP.FUt0lq7xZDgngJ2zoh8lFAHaL0?w=802&h=1280&rs=1&pid=ImgDetMain");
            img = ImageIO.read(url);

        }
        catch (IOException e) {
            System.out.println("Errore");
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(img, x, y, LUNGHEZZA, ALTEZZA, null);
    }

    public void removeImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle r = new Rectangle(x, y, LUNGHEZZA + 1, ALTEZZA + 1);
        g2.setColor(Color.black);
        g2.fill(r);
    }




    public boolean isMoving() {
        // TODO Auto-generated method stub
        if(velocitaX == 0 && velocitaY == 0)
            return true;
        return false;
    }
}