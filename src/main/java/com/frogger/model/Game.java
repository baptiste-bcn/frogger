package com.frogger.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Grid grid;
    private final List<Obstacle> obstacles;

    public Game() {
        this.grid = new Grid(18, 19);
        this.obstacles = new ArrayList<>();

        initializeObstacles();
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    private void initializeObstacles() {
        for (int row = 0; row < grid.getHeight(); row++) {
            Grid.RowType rowType = grid.getRowType(row);

            if (rowType == Grid.RowType.SAFE) {
                // Ajouter 2 arbres aléatoires sur une ligne SAFE
                for (int i = 0; i < 2; i++) {
                    int x = (int) (Math.random() * grid.getWidth());
                    obstacles.add(new Obstacle(x, row, 0, Obstacle.ObstacleType.TREE));
                }
            } else if (rowType == Grid.RowType.ROAD) {
                // Ajouter une voiture sur une ligne ROAD
                int x = (int) (Math.random() * grid.getWidth());
                int speed = Math.random() < 0.5 ? 1 : -1; // Déplacement vers la droite ou la gauche
                obstacles.add(new Obstacle(x, row, speed, Obstacle.ObstacleType.CAR));
            }
        }
    }

    public void updateObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.move(grid.getWidth());
        }
    }
}
