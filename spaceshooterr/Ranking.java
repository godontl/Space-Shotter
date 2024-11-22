package it.unibs.fp.spaceshooterr;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking {
    private ArrayList<Player> players;  // Lista dei giocatori

    public Ranking() {
        players = new ArrayList<>();
    }

    // Aggiungi un nuovo giocatore alla classifica
    public void addPlayer(Player player) {
        players.add(player);
        sortRanking();  // Ordina la classifica ogni volta che un giocatore viene aggiunto
    }

    // Ordina i giocatori in base al punteggio (decrescente)
    private void sortRanking() {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                // Ordina per punteggio (decrescente)
                return Integer.compare(p2.getScore(), p1.getScore());
            }
        });
    }

    // Ottieni la classifica completa come stringa
    public String getRanking() {
        StringBuilder rankingStr = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            rankingStr.append(i + 1).append(". ").append(player.getName())
                    .append(" - ").append(player.getScore()).append(" punti\n");
        }
        return rankingStr.toString();
    }

    // Ottieni il miglior punteggio
    public Player getTopPlayer() {
        if (!players.isEmpty()) {
            return players.get(0);  // Il primo giocatore è il top player
        }
        return null;  // Nessun giocatore
    }

    // Mostra la classifica
    public void printRanking() {
        System.out.println(getRanking());
    }
}
