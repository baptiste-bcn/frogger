package com.frogger.model;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import java.util.Random;

public class Grille {
    private final int width;
    private final int height;
    private List<Obstacle> obstacles;

    public Grille(int width, int height) {
        this.width = width;
        this.height = height;
        // Initialiser les obstacles ou autres éléments nécessaires ici
        this.obstacles = new ArrayList<>();
        generateRandomObstacles();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    private void generateRandomObstacles() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            int obstacleWidth = 1;
            int speed = rand.nextInt(3);
            Color color = Color.GREEN; 
            obstacles.add(new Obstacle(this, x, y, obstacleWidth, speed, color));
        }
    }
}
