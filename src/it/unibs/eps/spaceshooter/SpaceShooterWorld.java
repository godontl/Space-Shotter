package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import static it.unibs.eps.spaceshooter.MessageButton.*;

public class SpaceShooterWorld extends JFrame {
    public static final int HEIGHT_FRAME = 700;
    public static final int WIDTH_FRAME = 400;
    public static final String GAME_TITLE = "Space Shooter";

    private JTextField nameField; // Campo per inserire il nome

    public SpaceShooterWorld() {
        setTitle(GAME_TITLE);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creazione del pannello principale
        JPanel panel = new JPanel(new GridBagLayout());

        // Creazione testo benvenuto
        ComponentWithConstraints welcomeText = createText("Benvenuto in " + GAME_TITLE + "!", "Arial", Font.BOLD, 20, 0, 0);
        panel.add(welcomeText.component, welcomeText.constraints);

        // Creazione etichetta per il nome
        ComponentWithConstraints nameLabel = createText("Inserisci il tuo nome:", "Arial", Font.PLAIN, 14, 0, 1);
        panel.add(nameLabel.component, nameLabel.constraints);

        // Creazione campo di testo per il nome
        nameField = new JTextField(15);
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.gridx = 0;
        fieldConstraints.gridy = 2;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);
        panel.add(nameField, fieldConstraints);

        // Creazione del pulsante "Inizia a giocare!"
        ComponentWithConstraints startButton = createButton("Inizia a giocare!", 200, 50, e -> startGame(), "Arial", Font.BOLD, 18, 0, 3);
        panel.add(startButton.component, startButton.constraints);

        // Creazione testo crediti
        ComponentWithConstraints creditText = createText("by NovaCode", "SanSerif", Font.ITALIC, 10, 0, 4);
        panel.add(creditText.component, creditText.constraints);

        // Aggiunta del pannello al frame
        add(panel);
        setVisible(true);
    }

    private void startGame() {
        String playerName = nameField.getText().trim();
        int playerScore=0;
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, inserisci il tuo nome!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Il gioco inizierà dopo aver confermato!");
        dispose();
        new Game(playerName, playerScore); // Passa il nome del giocatore al gioco
    }
}