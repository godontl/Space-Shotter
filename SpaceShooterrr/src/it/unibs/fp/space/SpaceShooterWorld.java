
package it.unibs.fp.space;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import java.awt.*;
import static it.unibs.fp.space.MessageButton.*;

public class SpaceShooterWorld extends JFrame {
    public static final int HEIGHT_FRAME = 700;
    public static final int WIDTH_FRAME = 400;
    public static final String GAME_TITLE = "Space Shooter";

    private JTextField nameField; // Campo per inserire il nome
    private static Ranking ranking;

    public SpaceShooterWorld() {

        setTitle(GAME_TITLE);
        setSize(HEIGHT_FRAME, WIDTH_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creazione del pannello principale
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);


        // Creazione testo benvenuto
        ComponentWithConstraints welcomeText = createText("Benvenuto in " + GAME_TITLE + "!", customFont.getName(), Font.BOLD, 25, 0, 0, Color.LIGHT_GRAY);
        panel.add(welcomeText.component, welcomeText.constraints);

        // Creazione etichetta per il nome
        ComponentWithConstraints nameLabel = createText("Inserisci il tuo nome:", littlecustomFont.getName(), Font.PLAIN, 18, 0, 1, Color.WHITE);
        panel.add(nameLabel.component, nameLabel.constraints);

        // Creazione campo di testo per il nome
        nameField = new JTextField(15);
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.gridx = 0;
        fieldConstraints.gridy = 2;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);
        panel.add(nameField, fieldConstraints);

        // Creazione del pulsante "Inizia a giocare!"
                ComponentWithConstraints startButton = createButton("Inizia a giocare!", this::startGame , smallcustomFont.getFontName(), Font.BOLD, 18, 0, 3, Color.LIGHT_GRAY, Color.DARK_GRAY);
        panel.add(startButton.component, startButton.constraints);

        // Creazione testo crediti
        ComponentWithConstraints creditText = createText("by NovaCode", "SanSerif", Font.ITALIC, 10, 0, 4, Color.WHITE);
        panel.add(creditText.component, creditText.constraints);

        // Aggiunta del pannello al frame
        add(panel);
        setVisible(true);

        this.ranking = new Ranking();


    }

    public static Ranking getRanking() {
        return ranking;
    }

    void startGame() {
        String playerName = nameField.getText().trim();
        int playerScore=0;
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, inserisci il tuo nome!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //JOptionPane.showMessageDialog(this, "Il gioco inizierà dopo aver confermato!");
        dispose();
        new Game(playerName, playerScore); // Passa il nome del giocatore al gioco
    }
}
