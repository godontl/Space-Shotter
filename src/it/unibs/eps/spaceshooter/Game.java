package it.unibs.eps.spaceshooter;

import javax.swing.*;

import it.unibs.eps.spaceshooter.Asteroide;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import static it.unibs.eps.spaceshooter.MessageButton.*;
import static it.unibs.eps.spaceshooter.SpaceShooterWorld.*;


public class Game extends JFrame {
    private final GamePanel gamePanel;
    private final GamePanel punteggioPanel;
    private final Astronave astronave;
    private ArrayList<Proiettile> proiettili = new ArrayList<Proiettile>();
    private ArrayList<Asteroide> asteroidi = new ArrayList<Asteroide>();
    private final JLabel punteggio;
    private final JLabel name;
    private boolean youLoser = false;
    private JTextField nameField;
    private JTextField scoreField;
    private JLabel timeLabel;
    private int gameTime = 0;
    private Ranking ranking;
    private Player player;

    // Modificato il costruttore per utilizzare la variabile player come attributo
    public Game(String playerName, int playerScore) {
        this.player = new Player(playerName, playerScore);
        this.ranking = SpaceShooterWorld.getRanking();

        // Configurazione della finestra di gioco
        setTitle(GAME_TITLE + " - Gioco");
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        punteggioPanel = new GamePanel();
        punteggioPanel.setSize(300, 300);
        add(punteggioPanel, BorderLayout.NORTH);

        punteggio = new JLabel("PUNTEGGIO: " + player.getScore() + "   ");
        punteggioPanel.add(punteggio);

        name = new JLabel("GIOCATORE: " + player.getName() + "  ");
        punteggioPanel.add(name);

        timeLabel = new JLabel("TEMPO: 0s");
        punteggioPanel.add(timeLabel);

        gamePanel = new GamePanel();

        add(gamePanel);

        astronave = new Astronave();

        setVisible(true);

        startGameLoop();
    }

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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) proiettili.add(new Proiettile(astronave.getXMedia(), astronave.getY()));
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) astronave.setVelocitaX(5);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) astronave.setVelocitaX(-5);
                if (e.getKeyCode() == KeyEvent.VK_UP) astronave.setVelocitaY(-5);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) astronave.setVelocitaY(5);
            }
        });

        // Timer per aggiornare il gioco ogni 10 ms (100 FPS circa)
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameTime == 0) astronave.paintImage(getGraphics());


                if(gameTime % 100 == 0)
                    asteroidi.add(new Asteroide((int)(Math.random()*300), 0));

                Iterator<Asteroide> i1 = asteroidi.iterator();
                while(i1.hasNext()) {
                    Asteroide a = i1.next();
                    if(a.getY() > HEIGHT_FRAME) {
                        i1.remove();
                        a.paintComponent(getGraphics(), Color.black);
                    }
                    else {
                        a.paintComponent(getGraphics(), Color.black);
                        a.run();
                        a.paintComponent(getGraphics(), Color.blue);
                    }
                }





                // Movimentazione e disegno dei proiettili
                Iterator<Proiettile> i2 = proiettili.iterator();
                while(i2.hasNext()) {
                    Proiettile p = i2.next();
                    if(p.getY() < 100) {
                        p.paintComponent(getGraphics(), Color.black);
                        i2.remove();
                    }
                    else {
                        p.paintComponent(getGraphics(), Color.black);
                        p.run();
                        p.paintComponent(getGraphics(), Color.blue);
                    }
                }

                if (!astronave.isMoving()) {
                    astronave.removeImage(getGraphics());
                    astronave.move(gamePanel.getBounds());
                    astronave.paintImage(getGraphics());
                }

                // Incrementa il tempo di gioco e aggiorna l'etichetta
                gameTime++;
                player.addScore(gameTime / 100);  // Incrementa il punteggio in base al tempo (modificabile)

                timeLabel.setText(" TEMPO: " + gameTime / 100 + "s");
                punteggio.setText("PUNTEGGIO: " + player.getScore() + "   "); // Aggiorna il punteggio visibile

                punteggioPanel.repaint();

                // Condizione di fine gioco
                if (gameTime >= 1000) {
                    youLoser = true;
                }

                if (youLoser) {
                    endGame();
                    ((Timer) e.getSource()).stop();  // Ferma il timer
                }
            }
        });

        timer.start();
    }

    // Metodo per terminare il gioco
    private void endGame() {
        ranking.addPlayer(player);  // Aggiungi il giocatore alla classifica
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel endPanel = new JPanel(new GridBagLayout());

        // Testo di fine gioco
        ComponentWithConstraints lostTextComponent = createText("Hai perso!", "Arial", Font.BOLD, 20, 0, 0);
        endPanel.add(lostTextComponent.component, lostTextComponent.constraints);

        // Crediti
        ComponentWithConstraints creditTextComponent = createText("by NovaCode", "SanSerif", Font.ITALIC, 10, 0, 4);
        endPanel.add(creditTextComponent.component, creditTextComponent.constraints);

        // Creazione pulsanti
        ComponentWithConstraints restartButtonComponent = createButton("Fai un'altra partita", 300, 25, e -> restartGame(), "Arial", Font.BOLD, 15, 0, 1);
        endPanel.add(restartButtonComponent.component, restartButtonComponent.constraints);

        ComponentWithConstraints rankingButtonComponent= createButton("Guarda classifica", 300,25, e -> rankingGame(), "Arial", Font.BOLD, 15, 0, 2);
        endPanel.add(rankingButtonComponent.component, rankingButtonComponent.constraints);

        ComponentWithConstraints endButtonComponent = createButton("Esci", 300, 25, e -> closeGame(), "Arial", Font.BOLD, 15, 0, 3);
        endPanel.add(endButtonComponent.component, endButtonComponent.constraints);

        add(endPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Metodo per riavviare il gioco
    private void restartGame() {
        JTextField nameField = new JTextField(player.getName()); // Precompila con il nome attuale
        int option = JOptionPane.showConfirmDialog(this, nameField, "Inserisci il nome del nuovo giocatore", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String newName = nameField.getText().trim();
            if (!newName.isEmpty()) {
                player.setName(newName); // Aggiorna il nome del giocatore
            } else {
                JOptionPane.showMessageDialog(this, "Il nome non può essere vuoto! Verrà mantenuto il nome attuale.", "Errore", JOptionPane.ERROR_MESSAGE
                );
            }
        }
        dispose();
        new Game(player.getName(), 0);
    }

    // Metodo per chiudere il gioco
    private void closeGame() {
        dispose();
        System.exit(0);
    }

    // Metodo per la gestione della classifica (se desiderato)
    private void rankingGame() {
        ranking.showRanking();  // Mostra la classifica (modifica questa parte per visualizzare la classifica)
    }

    // Classe interna per il pannello di gioco
    private static class GamePanel extends JPanel {
        public GamePanel() {
            setBackground(Color.black);
        }

        // Metodo per disegnare gli elementi di gioco
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.drawString("by NovaCode", 300, 650);  // Testo di esempio
        }
    }
}