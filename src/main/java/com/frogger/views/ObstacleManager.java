package com.frogger.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.frogger.models.Frog;
import com.frogger.models.Obstacle;

public class ObstacleManager {
    private Pane gamePane;
    private List<Obstacle> obstacles = new ArrayList<>();
    private Random random = new Random();

    public ObstacleManager(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void createInitialObstacles() {
        // Create cars in road rows
        for (int i = 1; i < 14; i++) {
            if (i % 5 != 0 && i != 0 && i != 14) {
                createObstaclesInRow(i, i < 7 ? ObstacleType.LOG : ObstacleType.CAR);
            }
        }
    }

    private void createObstaclesInRow(int row, ObstacleType type) {
        int count = random.nextInt(3) + 2; // 2-4 obstacles per row
        int direction = row % 2 == 0 ? 1 : -1; // Alternate directions
        double speed = (random.nextDouble() * 2 + 1) * direction; // 1-3 speed

        for (int i = 0; i < count; i++) {
            int width = type == ObstacleType.CAR ? 80 : 120;
            int spacing = 800 / count;
            int xPos = i * spacing + random.nextInt(spacing - width);

            Obstacle obstacle = new Obstacle(type, row * 40, xPos, width, 40, speed);
            obstacles.add(obstacle);
            gamePane.getChildren().add(obstacle);
        }
    }

    public void updateObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.move();
        }
    }

    public boolean checkCollision(Frog frog) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.intersects(frog.getBoundsInParent())) {
                if (obstacle.getType() == ObstacleType.CAR) {
                    return true; // Collision with car
                } else if (obstacle.getType() == ObstacleType.LOG) {
                    // Move with log
                    frog.setX(frog.getX() + obstacle.getSpeed());
                    return false;
                }
            }
        }

        // Check water collision when not on log
        // Check water collision when not on log
        int row = (int) (frog.getY() / 40);
        if (row > 0 && row < 7 && row % 5 != 0) {
            // Check if frog is on any log
            for (Obstacle log : obstacles) {
                if (log.getType() == ObstacleType.LOG && log.intersects(frog.getBoundsInParent())) {
                    return false; // On log, safe
                }
            }
            return true; // In water but not on log, collision
        }

        return false;
    }

    public enum ObstacleType {
        CAR, LOG
    }
}