package com.frogger.model;

public class Player {
    private int x;
    private int y;
    private int previousX;
    private int previousY;
    private int score;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
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