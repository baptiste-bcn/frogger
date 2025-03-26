package com.frogger.model;

import javafx.scene.paint.Color;

import java.util.Random;

public class Obstacle {
    private int x, y;
    private final int width;
    private final float speed;
    private Color color;
    private final Grille grille;
    private final Random random = new Random();

    public Obstacle(Grille grille, int x, int y, int width, float speed, Color color) {
        this.grille = grille;
        this.x = x;
        this.y = y;
        this.width = width;
        this.speed = speed;
        this.color = speed == 0 ? color : getRandomColor();
    }

    private Color getRandomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
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
        return this.speed == 0; //Pour gerer colision avec buissons plus tard
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean collidesWith(Joueur joueur) {
        return joueur.getX() >= x && joueur.getX() < (x + width) && joueur.getY() == y;
    }
}
