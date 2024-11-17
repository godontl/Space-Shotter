package it.unibs.eps.spaceshooter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static it.unibs.eps.spaceshooter.MessageButton.*;
import static it.unibs.eps.spaceshooter.SpaceShooterWorld.*;

public class Game extends JFrame {


    private final GamePanel gamePanel;
    private final GamePanel punteggioPanel;
    private final Astronave astronave;
    // private Proiettile p;
    private final JLabel punteggio;
    private boolean youLoser = false;
    private int timeCounter = 0;
    private Timer gameTimer;


    public Game() {
        // Configurazione della finestra di gioco
        setTitle(GAME_TITLE + " - Gioco");
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        punteggioPanel = new GamePanel();
        punteggioPanel.setSize(100, 300);
        add(punteggioPanel, BorderLayout.NORTH);

        punteggio = new JLabel("PUNTEGGIO: ");
        punteggioPanel.add(punteggio);

        gamePanel = new GamePanel();

        add(gamePanel);


        astronave = new Astronave();
        gamePanel.add(astronave);


        setVisible(true);

        startGameLoop();
    }


    // da qui in poi da modificare (lavoro di ChatGPT)
    // Metodo per avviare il ciclo di gioco
    private void startGameLoop() {
        addKeyListener(new KeyListener() {


            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

                System.out.println(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    astronave.setVelocitaX(0);
//				astronave.paintComponent(getGraphics(), Color.black);
//				astronave.moveRight(gamePanel.getBounds());
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    astronave.setVelocitaX(0);
//				astronave.paintComponent(getGraphics(), Color.black);
//				astronave.moveLeft(gamePanel.getBounds());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    astronave.setVelocitaY(0);
//				astronave.paintComponent(getGraphics(), Color.black);
//				astronave.moveUp(gamePanel.getBounds());
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    astronave.setVelocitaY(0);
//					astronave.paintComponent(getGraphics(), Color.black);
//					astronave.moveDown(gamePanel.getBounds());
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
/*
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					Proiettile p = new Proiettile(astronave.getXMedia(), astronave.getY());
					p.paintComponent(getGraphics());
					gamePanel.add(p);
				}
*/


                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    astronave.setVelocitaX(10);
                    astronave.paintComponent(getGraphics(), Color.black);
                    astronave.move(gamePanel.getBounds());
//					astronave.moveRight(gamePanel.getBounds());
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    astronave.setVelocitaX(-10);
                    astronave.paintComponent(getGraphics(), Color.black);
                    astronave.move(gamePanel.getBounds());
//					astronave.moveLeft(gamePanel.getBounds());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    astronave.setVelocitaY(-10);
                    astronave.paintComponent(getGraphics(), Color.black);
                    astronave.move(gamePanel.getBounds());
//					astronave.moveUp(gamePanel.getBounds());
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    astronave.setVelocitaY(10);
                    astronave.paintComponent(getGraphics(), Color.black);
                    astronave.move(gamePanel.getBounds());
//					astronave.moveDown(gamePanel.getBounds());
                }


            }
        });


        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeCounter++;
//                p.moveUp();
                astronave.paintComponent(getGraphics(), Color.blue);
//                gamePanel.update  // Aggiorna lo stato del gioco
//                gamePanel.repaint(); // Ridisegna il pannello

                // Condizione di fine gioco
                if (timeCounter >= 10000) { // Condizione farlocca: perde dopo 10 secondi
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
            paintComponent(getGraphics());
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
