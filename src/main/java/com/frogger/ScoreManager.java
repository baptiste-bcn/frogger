package com.frogger;

public class ScoreManager {
    private int score;
    private int lives;

    public ScoreManager() {
        this.score = 0;
        this.lives = 3; // Nombre de vies initial
    }

    public void increaseScore() {
        score += 10; // Augmente le score de 10 points
    }

    public void loseLife() {
        lives--;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}