package com.frogger.model;

public class Game {
    private Grille grille;
    private boolean running;

    public Game(Grille grille) {
        this.grille = grille;
        this.running = true;
    }

    public Grille getGrille() {
        return grille;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopGame() {
        running = false;
    }
}
