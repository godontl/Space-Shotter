package it.unibs.eps.spaceshooter;

// menu principale, eventuale classifica (da implementare), costanti, boh

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.*;
import static it.unibs.eps.spaceshooter.MessageButton.*;


public class SpaceShooterWorld extends JFrame {
    public static final int HEIGHT_FRAME = 700;
    public static final int WIDTH_FRAME = 400;
    public static final String GAME_TITLE = "Space Shooter";

    // Altre costanti di gioco
    public static final int PLAYER_SPEED = 5;
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    public SpaceShooterWorld() {
        setTitle(GAME_TITLE);
        setSize(HEIGHT_FRAME, WIDTH_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creazione del pannello principale
        JPanel panel = new JPanel(new GridBagLayout());

        // Creazione testo benvenuto
        ComponentWithConstraints welcomeText = createText("Benvenuto in " + GAME_TITLE + "!", "Arial", Font.BOLD, 20, 0, 0);
        panel.add(welcomeText.component, welcomeText.constraints);

        // Creazione del pulsante "Inizia a giocare!"
        ComponentWithConstraints startButton = createButton("Inizia a giocare!", 200, 50, e -> startGame(), "Arial", Font.BOLD, 18, 0, 1);
        panel.add(startButton.component, startButton.constraints);

        // Creazione testo crediti
        ComponentWithConstraints creditText = createText("by NovaCode", "SanSerif", Font.ITALIC, 10, 0, 2);
        panel.add(creditText.component, creditText.constraints);


        //Visibilità pannello
        add(panel);
        setVisible(true);
    }

    private void startGame() {
        JOptionPane.showMessageDialog(this, "Il gioco inizierà una volta cliccato il tasto!");
        dispose();
        new Game();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpaceShooterWorld();
            }
        });
    }
}

