package com.frogger.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Obstacle {
    private int x, y;
    private final int width;
    private final int speed;
    private final Color color;
    private final Grille grille;

    public Obstacle(Grille grille, int x, int y, int width, int speed, Color color, boolean isWater) {
        this.grille = grille;
        this.x = x;
        this.y = y;
        this.width = width;
        this.speed = speed;
        this.color = color;
    }

    public void move() {
        x += speed;
        if (x > grille.getWidth()) {
            x = -width;
        } else if (x + width < 0) {
            x = grille.getWidth();
        }
    }

    public boolean isStatic() {
        return this.speed == 0; // Un obstacle est statique s’il ne bouge pas
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void draw(GraphicsContext gc, int tileSize) {
        gc.setFill(color);
        gc.fillRect(x * tileSize, y * tileSize, width * tileSize, tileSize);
    }

    public boolean collidesWith(Joueur joueur) {
        return joueur.getX() >= x && joueur.getX() < (x + width) && joueur.getY() == y;
    }
}
