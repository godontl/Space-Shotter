package it.unibs.eps.spaceshooter;




public class Player {
    private String name;
    private int score;

    // Costruttore per inizializzare il giocatore
    public Player(String name, int playerScore) {
        this.name = name;
        this.score = 0; // Il punteggio parte da zero
    }

    // Getter per il nome
    public String getName() {
        return name;
    }

    // Setter per il nome
    public void setName(String name) {
        this.name = name;
    }

    // Getter per il punteggio
    public int getScore() {
        return score/100;
    }

    // Metodo per aggiungere punti
    public void addScore(int points) {
        this.score += points;
    }

    // Metodo per resettare il punteggio
    public void resetScore() {
        this.score = 0;
    }

    // Metodo per rappresentare lo stato del giocatore come stringa
    @Override
    public String toString() {
        return "Player{name='" + name + "', score=" + score + "}";
    }

}
