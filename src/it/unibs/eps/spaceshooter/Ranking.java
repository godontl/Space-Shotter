package it.unibs.eps.spaceshooter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
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
    public void showRanking(Game game) {
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
        JButton closeButton = new JButton("Chiudi");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.RED);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> {
            rankingWindow.dispose();  // Chiudi la finestra della classifica
            game.restartGame();  // Chiama restartGame di Game per riavviare la partita
        });
        buttonPanel.add(closeButton);

        // Pulsante "Nuova Partita"
        JButton newGameButton = new JButton("Nuova Partita");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBackground(Color.GREEN);
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(e -> {
            rankingWindow.dispose();
            new SpaceShooterWorld();  // Avvia una nuova partita
        });
        buttonPanel.add(newGameButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Aggiungi il pannello principale alla finestra
        rankingWindow.add(mainPanel);

        // Mostra la finestra
        rankingWindow.setVisible(true);
    }
}
