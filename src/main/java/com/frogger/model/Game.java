package com.frogger.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Grid grid;
    private final List<Obstacle> obstacles;
    private final Player player1;
    private final Player player2;
    private boolean duoMode;

    public Game(boolean duoMode) {
        this.duoMode = duoMode;
        this.grid = new Grid(19, 15);
        this.obstacles = new ArrayList<>();

        int gridWidth = grid.getWidth();
        int player1X = gridWidth % 2 == 1 ? (isDuoMode() ? gridWidth / 2 - 1 : gridWidth / 2) : gridWidth / 2 - 1;
        int player2X = gridWidth % 2 == 0 ? gridWidth / 2 : gridWidth / 2 + 1;

        this.player1 = new Player(player1X, grid.getHeight() - 1);
        this.player2 = duoMode ? new Player(player2X, grid.getHeight() - 1) : null;

        initializeObstacles();
    }

    /**
     * ============================
     * GETTER SECTION
     * ============================
     **/

    public boolean isDuoMode() {
        return duoMode;
    }

    public Grid getGrid() {
        return grid;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    /**
     * ============================
     * GAME LOGIC SECTION
     * ============================
     **/

    public boolean hasFinished(Player player) {
        if (player == null) {
            return false;
        }

        if (player.getY() == 0) {
            player.setScore(player.getScore() + 100);

            int gridWidth = grid.getWidth();
            int startX = duoMode && player == player2 ? gridWidth / 2 + 1 : gridWidth / 2;
            player.resetPosition(startX, grid.getHeight() - 1);

            return true;
        }

        return false;
    }

    public void resetGame() {
        // Réinitialiser la grille
        this.grid = new Grid(grid.getWidth(), grid.getHeight());

        this.obstacles.clear();
        initializeObstacles();

        int gridWidth = grid.getWidth();
        int player1X = gridWidth % 2 == 1 ? (isDuoMode() ? gridWidth / 2 - 1 : gridWidth / 2) : gridWidth / 2 - 1;
        int player2X = gridWidth % 2 == 0 ? gridWidth / 2 : gridWidth / 2 + 1;

        player1.resetPosition(player1X, grid.getHeight() - 1);
        if (duoMode) {
            player2.resetPosition(player2X, grid.getHeight() - 1);
        }
    }

    private boolean isRestrictedZone(int x, int row) {
        int center = grid.getWidth() / 2;
        boolean isCenterRestricted = grid.getWidth() % 2 == 0
                ? x >= center - 1 && x <= center
                : x >= center - 1 && x <= center + 1;
        return isCenterRestricted
                && (row == 0 || row == 1 || row == grid.getHeight() - 1 || row == grid.getHeight() - 2);
    }

    /**
     * ============================
     * OBSTACLES SECTION
     * ============================
     **/

    private void initializeObstacles() {
        for (int row = 0; row < grid.getHeight(); row++) {
            Grid.RowType rowType = grid.getRowType(row);

            if (rowType == Grid.RowType.SAFE) {
                int treeCount = 4 + (int) (Math.random() * 5); // Random number between 3 and 6
                for (int i = 0; i < treeCount; i++) {
                    int x;
                    do {
                        x = (int) (Math.random() * grid.getWidth());
                    } while (isRestrictedZone(x, row));
                    obstacles.add(new Obstacle(x, row, 0, Obstacle.ObstacleType.TREE));
                }
            } else if (rowType == Grid.RowType.ROAD) {
                int x;
                do {
                    x = (int) (Math.random() * grid.getWidth());
                } while (isRestrictedZone(x, row));
                int speed = Math.random() < 0.5 ? 1 : -1;
                obstacles.add(new Obstacle(x, row, speed, Obstacle.ObstacleType.CAR));
            }
        }
    }

    public void updateObstacles() {
        for (Obstacle obstacle : obstacles) {
            obstacle.move(grid.getWidth());
        }
    }

    public boolean handleCollision(Player player) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == player.getX() && obstacle.getY() == player.getY()) {
                if (obstacle.getType() == Obstacle.ObstacleType.TREE) {
                    player.restorePreviousPosition();
                    return true;
                } else if (obstacle.getType() == Obstacle.ObstacleType.CAR) {
                    player.resetPosition(grid.getWidth() / 2 - 1, grid.getHeight() - 1);
                    player.resetScore();
                    return true;
                }
            }
        }
        return false; // Pas de collision
    }
}