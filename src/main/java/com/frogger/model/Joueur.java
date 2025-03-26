package com.frogger.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Joueur {
    private int x, y;
    private int score;
    private int highestRowReached;
    private final Grille grille;
    private final Color color;
    private int previousX;
    private int previousY;

    public Joueur(Grille grille, Color color) {
        this.grille = grille;
        this.color = color;
        this.x = grille.getWidth() / 2;
        this.y = grille.getHeight() - 1;
        this.score = 0;
        this.highestRowReached = this.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getScore() {
        return this.score;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.highestRowReached = y;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void annulerDeplacement() {
        this.x = previousX;
        this.y = previousY;
    }

    public void moveTop() {
        sauvegarderPosition();
        if (y > 0) {
            y--;
            if (y < highestRowReached) {
                highestRowReached = y;
                score++;
            }
        }
    }

    public void moveBottom() {
        sauvegarderPosition();
        if (y < grille.getHeight() - 1) y++;
    }

    public void moveLeft() {
        sauvegarderPosition();
        if (x > 0) x--;
    }

    public void moveRight() {
        sauvegarderPosition();
        if (x < grille.getWidth() - 1) x++;
    }

    private void sauvegarderPosition() {
        previousX = x;
        previousY = y;
    }
}