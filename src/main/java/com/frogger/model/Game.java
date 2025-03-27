package com.frogger.model;

public class Game {
    private Grid grid;
    private boolean running;

    public Game(Grid grid) {
        this.grid = grid;
        this.running = true;
    }

    public Grid getGrid() {
        return grid;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopGame() {
        running = false;
    }
}