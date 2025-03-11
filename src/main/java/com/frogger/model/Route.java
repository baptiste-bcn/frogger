package com.frogger.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final int y;
    private final int tileSize;
    private final List<Obstacle> obstacles;
    private final Grille grille;

    public Route(Grille grille, int y, int tileSize) {
        this.grille = grille;
        this.y = y;
        this.tileSize = tileSize;
        this.obstacles = new ArrayList<>();
    }

    public void ajouterObstacle(int x, int width, int speed, Color color) {
        obstacles.add(new Obstacle(grille, x, y, width, speed, color, false));
    }

    public void deplacerObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.move();
        }
    }

    public int getY() {
        return this.y;
    }

    // Nouvelle méthode pour récupérer les obstacles
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void draw(GraphicsContext gc, int scale) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, y * tileSize, grille.getWidth() * tileSize, tileSize);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(gc, tileSize);
        }
    }

    public boolean checkCollision(Joueur joueur) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collidesWith(joueur)) {
                return true;
            }
        }
        return false;
    }
}
