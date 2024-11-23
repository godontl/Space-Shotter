package it.unibs.eps.spaceshooter;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import static it.unibs.eps.spaceshooter.MessageButton.*;


public class Game extends JFrame {
    private final GamePanel gamePanel;
    private final JPanel punteggioPanel;
    private final Astronave astronave;
    private ArrayList<Proiettile> proiettili = new ArrayList<>();
    private ArrayList<Asteroide> asteroidi = new ArrayList<>();
    private final JLabel punteggioLabel;
    private final JLabel timeLabel;
    private final JLabel nameLabel;
    private int gameTime = 0;
    private boolean gameOver = false;

    private final Ranking ranking;
    private final Player player;

    public Game(String playerName, int playerScore) {
        this.player = new Player(playerName, playerScore);
        this.ranking = SpaceShooterWorld.getRanking();

        // Configurazione finestra
        setTitle(SpaceShooterWorld.GAME_TITLE + " - Gioco");
        setSize(SpaceShooterWorld.WIDTH_FRAME, SpaceShooterWorld.HEIGHT_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Pannello punteggio
        punteggioPanel = new JPanel();
        punteggioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        punteggioPanel.setBackground(Color.DARK_GRAY);

        nameLabel = new JLabel("Giocatore: " + player.getName()+ "   ");
        nameLabel.setForeground(Color.WHITE);
        punteggioPanel.add(nameLabel);

        punteggioLabel = new JLabel("Punteggio: " + player.getScore()+ "   ");
        punteggioLabel.setForeground(Color.WHITE);
        punteggioPanel.add(punteggioLabel);

        timeLabel = new JLabel("Tempo: 0s");
        timeLabel.setForeground(Color.WHITE);
        punteggioPanel.add(timeLabel);

        add(punteggioPanel, BorderLayout.NORTH);

        // Pannello di gioco
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Astronave
        astronave = new Astronave();

        setVisible(true);

        startGameLoop();
    }

    private void startGameLoop() {
        // Listener per i controlli
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    astronave.setVelocitaX(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    astronave.setVelocitaY(0);
                }
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

        // Timer di gioco
        Timer timer = new Timer(16, new ActionListener() { // Circa 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aggiorna lo stato di gioco
                updateGameState();

                // Richiama il rendering
                gamePanel.repaint();

                // Controlla condizione di fine gioco
                if (gameOver) {
                    ((Timer) e.getSource()).stop();
                    endGame();
                }
            }
        });

        timer.start();
    }

    private void updateGameState() {
        // Muovi l'astronave
        astronave.move(gamePanel.getBounds());

        // Muovi gli asteroidi
        Iterator<Asteroide> asteroidIterator = asteroidi.iterator();
        while (asteroidIterator.hasNext()) {
            Asteroide asteroide = asteroidIterator.next();

            // Ciclo interno: verifica collisione tra questo asteroide e tutti i proiettili
            Iterator<Proiettile> projectileIterator = proiettili.iterator();
            while (projectileIterator.hasNext()) {
                Proiettile proiettile = projectileIterator.next();

                if (asteroide.checkCollisionWithProjectile(proiettile)) {
                    // Collisione rilevata: rimuovi asteroide e proiettile
                    asteroidIterator.remove();
                    projectileIterator.remove();
                    player.addScore(5000); // Aggiungi punti per la distruzione dell'asteroide
                    break; // Esci dal ciclo dei proiettili (l'asteroide è già stato rimosso)
                }
            }

            // Controlla collisione con l'astronave
            if (asteroide.checkCollision(astronave)) {
                gameOver = true;
                endGame();
                return; // per uscire da updateGameState
            }

            // Muovi l'asteroide
            asteroide.run();

            // Rimuovi asteroidi fuori dallo schermo
            if (asteroide.getY() > gamePanel.getHeight()) {
                asteroidIterator.remove();
            }
        }


        // Muovi i proiettili
        Iterator<Proiettile> projectileIterator = proiettili.iterator();
        while (projectileIterator.hasNext()) {
            Proiettile proiettile = projectileIterator.next();
            proiettile.run();

            // Rimuovi proiettili fuori dallo schermo
            if (proiettile.getY() < 0) {
                projectileIterator.remove();
            }
        }

        // Genera nuovi asteroidi
        if (gameTime % 50 == 0) {
            asteroidi.add(new Asteroide((int) (Math.random() * gamePanel.getWidth()), 0));
        }

        gameTime++;
        player.addScore(gameTime / 100);  // Incrementa il punteggio in base al tempo (modificabile)

        timeLabel.setText(" TEMPO: " + gameTime / 60 + "s   ");
        punteggioLabel.setText("PUNTEGGIO: " + player.getScore() + "   "); // Aggiorna il punteggio visibile
    }

    private void endGame() {
        if (!ranking.getRankingList().contains(player)) {
            ranking.addPlayer(player);
        }
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
    public void restartGame() {
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
        if (ranking.getRankingList().size() > 0) {
            ranking.showRanking(this);  // Mostra la classifica
        }
    }

    // Classe per il rendering
    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Sfondo
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // Disegna l'astronave
            astronave.paintComponent(g, Color.RED);

            // Disegna asteroidi
            for (Asteroide asteroide : asteroidi) {
                asteroide.paintComponent(g, Color.GRAY);
            }

            // Disegna proiettili
            for (Proiettile proiettile : proiettili) {
                proiettile.paintComponent(g, Color.BLUE);
            }

        }
    }

}