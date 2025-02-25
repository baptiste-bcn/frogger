package com.frogger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Joueur {
    private int x, y;
    private final Grille grille;

    public Joueur(Grille grille) {
        this.grille = grille;
        this.x = grille.getWidth() / 2;  // Centre horizontal
        this.y = grille.getHeight() - 1; // En bas
    }

    public void moveTop() {
        if (y > 0) y--;
    }

    public void moveBottom() {
        if (y < grille.getHeight() - 1) y++;
    }

    public void moveLeft() {
        if (x > 0) x--;
    }

    public void moveRight() {
        if (x < grille.getWidth() - 1) x++;
    }

    public void dessiner(GraphicsContext gc, int tileSize) {
        gc.setFill(Color.GREEN);
        gc.fillOval(x * tileSize + 10, y * tileSize + 10, tileSize - 20, tileSize - 20);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
