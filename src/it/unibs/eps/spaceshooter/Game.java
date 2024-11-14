package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.unibs.eps.spaceshooter.MessageButton.*;
import static it.unibs.eps.spaceshooter.SpaceShooterWorld.*;

public class Game extends JFrame {

    private final GamePanel gamePanel;
    private boolean youLoser = false;
    private int timeCounter = 0;
    private Timer gameTimer;

    public Game() {
        // Configurazione della finestra di gioco
        setTitle(GAME_TITLE + " - Gioco");
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
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
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeCounter++;
                gamePanel.update(); // Aggiorna lo stato del gioco
                gamePanel.repaint(); // Ridisegna il pannello

                // Condizione di fine gioco
                if (timeCounter >= 10) { // Condizione farlocca: perde dopo 10 secondi
                    youLoser = true;
                }

                if (youLoser) {
                    endGame();
                    ((Timer) e.getSource()).stop(); // Ferma il timer
                }
            }
        });
        timer.start();
    }

    // Farina del mio sacco
    private void endGame() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel endPanel = new JPanel(new GridBagLayout());

        // Creazione testo sconfitta
        ComponentWithConstraints lostTextComponent = createText("Hai perso!", "Arial", Font.BOLD, 20, 0, 0);
        endPanel.add(lostTextComponent.component, lostTextComponent.constraints);

        // Creazione testo crediti
        ComponentWithConstraints creditTextComponent = createText("by NovaCode", "SanSerif", Font.ITALIC, 10, 0, 4);

        endPanel.add(creditTextComponent.component, creditTextComponent.constraints);

        // Creazione pulsanti
        ComponentWithConstraints restartButtonComponent = createButton("Fai un'altra partita", 300, 25, e -> restartGame(), "Arial", Font.BOLD, 15, 0, 1);
        endPanel.add(restartButtonComponent.component, restartButtonComponent.constraints);

        //JButton rankingButton= createButton("Guarda classifica", new Dimension(150,25), e -> (), "Arial", Font.BOLD, 18);

        ComponentWithConstraints endButtonComponent = createButton("Esci", 300, 25, e -> closeGame(), "Arial", Font.BOLD, 15, 0, 3);
        endPanel.add(endButtonComponent.component, endButtonComponent.constraints);

        add(endPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Metodo per riavviare il gioco
    private void restartGame() {
        dispose();
        new Game(); // Crea una nuova istanza del gioco
    }

    // Metodo per chiudere il gioco
    private void closeGame() {
        dispose();
        System.exit(0);
    }

    // Classe interna per il pannello di gioco
    private static class GamePanel extends JPanel {

        public GamePanel() {
            setBackground(BACKGROUND_COLOR);
        }

        // Metodo per aggiornare lo stato del gioco
        public void update() {
            //youLoser=true; // Qui potresti implementare la logica del gioco
        }

        // Metodo per disegnare gli elementi di gioco
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Disegna qui gli elementi di gioco
            g.setColor(Color.WHITE);
            g.drawString("by NovaCode", 300, 650); // Testo di esempio
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
