package com.frogger.model;

public class Player {
    private int x;
    private int y;
    private final int startX;
    private final int startY;
    private int previousX;
    private int previousY;
    private int score;

    // Best scores statiques pour chaque joueur
    private static int bestScoreJ1 = 0;
    private static int bestScoreJ2 = 0;
    private final boolean isPlayer2;

    public Player(int x, int y, int startX, int startY, boolean isPlayer2) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
        this.isPlayer2 = isPlayer2;
    }

    /**
     * ============================
     * POSITION SECTION
     * ============================
     **/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void savePreviousPosition() {
        this.previousX = this.x;
        this.previousY = this.y;
    }

    public void restoreStartPosition() {
        this.x = this.startX;
        this.y = this.startY;
    }

    public void restorePreviousPosition() {
        this.x = this.previousX;
        this.y = this.previousY;
    }

    public void resetPosition(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /**
     * ============================
     * SCORE SECTION
     * ============================
     **/

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void resetScore() {
        this.score = 0;
    }

    public int getBestScore() {
        return isPlayer2 ? bestScoreJ2 : bestScoreJ1;
    }

    public void updateBestScore() {
        if (isPlayer2) {
            if (score > bestScoreJ2)
                bestScoreJ2 = score;
        } else {
            if (score > bestScoreJ1)
                bestScoreJ1 = score;
        }
    }

    public boolean isPlayer2() {
        return isPlayer2;
    }

    /**
     * ============================
     * DEPLACEMENT SECTION
     * ============================
     **/

    public void moveUp() {
        if (y > 0) {
            y--;
        }
    }

    public void moveDown(int gridHeight) {
        if (y < gridHeight - 1) {
            y++;
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x--;
        }
    }

    public void moveRight(int gridWidth) {
        if (x < gridWidth - 1) {
            x++;
        }
    }

}