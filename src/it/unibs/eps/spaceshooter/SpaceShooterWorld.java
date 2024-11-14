package it.unibs.eps.spaceshooter;

// menu principale, eventuale classifica (da implementare), costanti, boh

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;






public class SpaceShooterWorld extends JFrame {
    public static final int WIDTH_FRAME = 700;
    public static final int HEIGHT_FRAME = 400;
    public static final String GAME_TITLE = "Space Shooter";

    // Altre costanti di gioco
    public static final int PLAYER_SPEED = 5;
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    public SpaceShooterWorld() {
        setTitle(GAME_TITLE);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creazione del pannello principale
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Creazione testo benvenuto
        JLabel welcomeText = new JLabel("Benvenuto in " +GAME_TITLE+"!");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeText.setHorizontalAlignment(JLabel.CENTER);

        // Creazione testo crediti
        JLabel creditText = new JLabel("by NovaCode");
        creditText.setFont(new Font("SanSerif", Font.ITALIC, 10));
        creditText.setHorizontalAlignment(JLabel.CENTER);
        creditText.setVerticalAlignment(JLabel.BOTTOM);

        // Aggiungi benvenuto al pannello
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(welcomeText, gbc);

        // Aggiungi crediti al pannello
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(creditText, gbc);

        // Creazione del pulsante "Inizia a giocare!"
        JButton startButton = new JButton("Inizia a giocare!");
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setFont(new Font("Arial", Font.BOLD, 18));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Aggiungi il pulsante al pannello
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(startButton, gbc);

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
