package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.unibs.eps.spaceshooter.SpaceShooterWorld.*;

public class Game extends JFrame {

    private GamePanel gamePanel;

    public Game() {
        // Configurazione della finestra di gioco
        setTitle(GAME_TITLE +"- Gioco");
        setSize(HEIGHT_FRAME, WIDTH_FRAME); //opposti così lungo, gg
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);

        startGameLoop();
    }

    // da qui in poi da modificare (lavoro di ChatGPT)
    // Metodo per avviare il ciclo di gioco
    private void startGameLoop() {
        Timer timer = new Timer(16, new ActionListener() { // Circa 60 FPS (1000 ms / 16 ≈ 60)
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.update(); // Aggiorna lo stato del gioco
                gamePanel.repaint(); // Ridisegna il pannello
            }
        });
        timer.start();
    }

    // Classe interna per il pannello di gioco
    private class GamePanel extends JPanel {

        public GamePanel() {
            setBackground(BACKGROUND_COLOR); // Imposta lo sfondo
        }

        // Metodo per aggiornare lo stato del gioco
        public void update() {

        }

        // Metodo per disegnare gli elementi di gioco
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Disegna qui gli elementi di gioco
            g.setColor(Color.WHITE);
            g.drawString("by NovaCode",300,650); // Testo di esempio
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game(); // Avvia la finestra di gioco
            }
        });
    }
}