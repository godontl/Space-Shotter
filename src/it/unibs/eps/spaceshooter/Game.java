package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static it.unibs.eps.spaceshooter.MessageButton.*;
import static it.unibs.eps.spaceshooter.SpaceShooterWorld.*;

public class Game extends JFrame {

    private final GamePanel gamePanel;
    private final GamePanel punteggioPanel;
    private final Astronave astronave;
    private ArrayList<Proiettile> proiettili = new ArrayList<Proiettile>();
    private final JLabel punteggio;
    private boolean youLoser = false;
    private int timeCounter = 0;
    private Timer gameTimer;
    private ArrayList<Asteroide> asteroidi = new ArrayList<>(); // Lista per gli asteroidi

    public Game() {
        setTitle(GAME_TITLE + " - Gioco");
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        punteggioPanel = new GamePanel();
        punteggioPanel.setSize(300, 300);
        add(punteggioPanel, BorderLayout.NORTH);

        punteggio = new JLabel("PUNTEGGIO: ");
        punteggioPanel.add(punteggio);

        gamePanel = new GamePanel();
        add(gamePanel);

        astronave = new Astronave();

        setVisible(true);

        startGameLoop();
    }

    // da qui in poi da modificare (lavoro di ChatGPT)
    // Metodo per avviare il ciclo di gioco
    private void startGameLoop() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) astronave.setVelocitaX(0);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) astronave.setVelocitaX(0);
                if (e.getKeyCode() == KeyEvent.VK_UP) astronave.setVelocitaY(0);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) astronave.setVelocitaY(0);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    proiettili.add(new Proiettile(astronave.getXMedia(), astronave.getY()));
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) astronave.setVelocitaX(5);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) astronave.setVelocitaX(-5);
                if (e.getKeyCode() == KeyEvent.VK_UP) astronave.setVelocitaY(-5);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) astronave.setVelocitaY(5);
            }
        });

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeCounter == 0) astronave.paintImage(getGraphics());
                timeCounter++;

                // Genera un asteroide ogni 200 cicli
                if (timeCounter % 200 == 0) {
                    int xPos = (int) (Math.random() * (WIDTH_FRAME - 50)); // Posizione casuale
                    asteroidi.add(new Asteroide(xPos, 0)); // Aggiunge un nuovo asteroide
                }

                // Muove gli asteroidi verso il basso
                for (int i = 0; i < asteroidi.size(); i++) {
                    Asteroide asteroide = asteroidi.get(i);

                    asteroide.paintComponent(getGraphics(), Color.black);  // Cancella la posizione precedente
                    asteroide.moveDown();  // L'asteroide scende
                    asteroide.paintComponent(getGraphics(), Color.gray);  // Ridisegna l'asteroide

                    // Rimuove gli asteroidi che escono dallo schermo
                    if (asteroide.getY() > HEIGHT_FRAME) {
                        asteroidi.remove(i);
                        i--;  // Decrementa l'indice per evitare di saltare l'asteroide successivo
                    }
                }

                // Muove i proiettili
                for (int i = 0; i < proiettili.size(); i++) {
                    Proiettile p = proiettili.get(i);
                    p.paintComponent(getGraphics(), Color.black);  // Cancella la posizione precedente
                    p.moveUp();  // Muove il proiettile verso l'alto
                    p.paintComponent(getGraphics(), Color.blue);  // Ridisegna il proiettile

                    // Rimuove il proiettile se esce dallo schermo
                    if (p.getY() < 0) {
                        proiettili.remove(i);
                        i--;  // Decrementa l'indice per evitare di saltare il proiettile successivo
                    }
                }

                // Muove e ridisegna l'astronave
                if (!astronave.isMoving()) {
                    astronave.removeImage(getGraphics());
                    astronave.move(gamePanel.getBounds());
                    astronave.paintImage(getGraphics());
                }

                // Gestione della fine del gioco
                if (youLoser) {
                    endGame();
                    ((Timer) e.getSource()).stop();  // Ferma il timer quando il gioco finisce
                }
            }
        });
        timer.start();
    }

    // Gestisce la fine del gioco
    private void endGame() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel endPanel = new JPanel(new GridBagLayout());

        ComponentWithConstraints lostTextComponent = createText("Hai perso!", "Arial", Font.BOLD, 20, 0, 0);
        endPanel.add(lostTextComponent.component, lostTextComponent.constraints);

        ComponentWithConstraints creditTextComponent = createText("by NovaCode", "SanSerif", Font.ITALIC, 10, 0, 4);
        endPanel.add(creditTextComponent.component, creditTextComponent.constraints);

        ComponentWithConstraints restartButtonComponent = createButton("Fai un'altra partita", 300, 25, e -> restartGame(), "Arial", Font.BOLD, 15, 0, 1);
        endPanel.add(restartButtonComponent.component, restartButtonComponent.constraints);

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

    private static class GamePanel extends JPanel {
        public GamePanel() {
            setBackground(BACKGROUND_COLOR);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.drawString("by NovaCode", 300, 650); // Testo di esempio
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game()); // Avvia il gioco
    }
}

