package it.unibs.eps.spaceshooter;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import it.unibs.eps.spaceshooter.MessageButton;

import static it.unibs.eps.spaceshooter.MessageButton.createButton;
import static it.unibs.eps.spaceshooter.MessageButton.smallcustomFont;

public class Ranking implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Player> rankingList;

    public Ranking() {
        rankingList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        Player newPlayer = new Player(player.getName(), player.getScore());
        rankingList.add(player);
        rankingList.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
    }

    public List<Player> getRankingList() {
        return rankingList;
    }

    // Metodo per visualizzare la classifica in una finestra
    public void showRanking(Game game, SpaceShooterWorld spaceShooterWorld) {
        // Crea una finestra per la classifica
        JFrame rankingWindow = new JFrame("Classifica");
        rankingWindow.setSize(500, 400);
        rankingWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rankingWindow.setLocationRelativeTo(null);

        // Pannello principale con layout verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        // Titolo della finestra
        JLabel titleLabel = new JLabel("Classifica Giocatori", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabella per visualizzare i giocatori
        String[] columns = {"Posizione", "Giocatore", "Punteggio"};
        Object[][] data = new Object[rankingList.size()][3];

        for (int i = 0; i < rankingList.size(); i++) {
            Player player = rankingList.get(i);
            data[i][0] = i + 1;  // Posizione
            data[i][1] = player.getName();  // Nome
            data[i][2] = player.getScore();  // Punteggio
        }

        JTable rankingTable = new JTable(data, columns);
        rankingTable.setFont(new Font("Arial", Font.PLAIN, 14));
        rankingTable.setRowHeight(25);
        rankingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        rankingTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        rankingTable.getTableHeader().setForeground(Color.BLACK);
        rankingTable.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(rankingTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Pannello per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.DARK_GRAY);


        // Pulsante "Chiudi"
        MessageButton.ComponentWithConstraints closeButtonComponent = createButton("Chiudi", game::restartGame, smallcustomFont.getFontName(), Font.BOLD, 14, 0, 3, Color.RED, Color.RED);
        buttonPanel.add(closeButtonComponent.component, closeButtonComponent.constraints);

        // Pulsante "Nuova Partita"
        MessageButton.ComponentWithConstraints newGameComponent = createButton("Nuova Partita", spaceShooterWorld::startGame, smallcustomFont.getFontName(), Font.BOLD, 14, 0, 3, Color.GREEN, Color.GREEN);
        buttonPanel.add(newGameComponent.component, newGameComponent.constraints);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Aggiungi il pannello principale alla finestra
        rankingWindow.add(mainPanel);

        // Mostra la finestra
        rankingWindow.setVisible(true);
    }

    public String toString(){
        return rankingList.toString();
    }
}
