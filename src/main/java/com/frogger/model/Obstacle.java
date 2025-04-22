package com.frogger.model;

public class Obstacle {
    private int x;
    private final int y;
    private final double speed;
    private final ObstacleType type;

    public enum ObstacleType {
        TREE, CAR
    }

    public Obstacle(int x, int y, double speed, ObstacleType type) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
    }

    /**
     * ============================
     * GETTER SECTION
     * ============================
     **/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public ObstacleType getType() {
        return type;
    }

    /**
     * ============================
     * DEPLACEMENT SECTION
     * ============================
     **/

    public void move(int gridWidth) {
        if (type == ObstacleType.CAR) {
            x += (int) speed;
            if (x < 0) {
                // On revient à droite de la grille
                x = gridWidth - 1;
            } else if (x >= gridWidth) {
                // On revient à gauche de la grille
                x = 0;
            }
        }
    }
}
