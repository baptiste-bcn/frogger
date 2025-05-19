package com.frogger.model;

import java.util.ArrayList;
import java.util.List;

import com.frogger.controller.SettingsController;

public class Game {
    private Grid grid;
    private final List<Obstacle> obstacles;
    private final Player player1;
    private Player player2 = null;
    private final boolean duoMode;
    public final static int GRID_WIDTH = 25;
    public final static int GRID_HEIGHT = 15;

    public Game(boolean duoMode) {
        this.duoMode = duoMode;
        this.grid = new Grid(GRID_WIDTH, GRID_HEIGHT);
        this.obstacles = new ArrayList<>();

        int startY = grid.getHeight() - 1;

        int P1StartX = duoMode ? GRID_WIDTH / 2 - 1 : GRID_WIDTH / 2;
        this.player1 = new Player(0, 0, P1StartX, startY, false);

        if (duoMode) {
            int P2startX = GRID_WIDTH / 2 + 1;
            this.player2 = new Player(0, 0, P2startX, startY, true);
        }

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
            player.updateBestScore();
            return true;
        }

        return false;
    }

    public void resetGame() {
        // Réinitialiser la grille
        this.grid = new Grid(grid.getWidth(), grid.getHeight());

        this.obstacles.clear();
        initializeObstacles();

        resetBothPlayerPositions();
    }

    private boolean isRestrictedZone(int x, int row) {
        int center = grid.getWidth() / 2;
        boolean isCenterRestricted = false;

        if (grid.getWidth() % 2 == 0) {
            if (x == center || x == center - 1) {
                isCenterRestricted = true;
            }
        } else {
            if (x >= center - 1 && x <= center + 1) {
                isCenterRestricted = true;
            }
        }

        boolean isRowRestricted = row <= 1 || row >= grid.getHeight() - 2;

        return isCenterRestricted && isRowRestricted;
    }

    /**
     * ============================
     * OBSTACLES SECTION
     * ============================
     **/

    private void initializeObstacles() {
        Difficulty difficulty = SettingsController.getSelectedDifficulty(); // Récupérer la difficulté sélectionnée

        for (int row = 0; row < grid.getHeight(); row++) {
            Grid.RowType rowType = grid.getRowType(row);

            if (rowType == Grid.RowType.SAFE) {
                int treeCount = 8 + (int) (Math.random() * 6);

                for (int i = 0; i < treeCount; i++) {
                    int x = (int) (Math.random() * grid.getWidth());

                    while (isRestrictedZone(x, row)) {
                        x = (int) (Math.random() * grid.getWidth());
                    }

                    obstacles.add(new Obstacle(x, row, 0, Obstacle.ObstacleType.TREE));
                }
            } else if (rowType == Grid.RowType.ROAD) {
                int speed = Math.random() < 0.5 ? 1 : -1;

                // Déterminer le nombre de voitures en fonction de la difficulté
                int numCars = switch (difficulty) {
                    case EASY -> 1 + (int) (Math.random() * 2); // 1 à 2 voitures
                    case HARD -> 2 + (int) (Math.random() * 3); // 2 à 4 voitures
                    default -> 2 + (int) (Math.random() * 2); // 2 à 3 voitures
                };

                for (int i = 0; i < numCars; i++) {
                    int x = (int) (Math.random() * grid.getWidth());

                    while (isRestrictedZone(x, row)) {
                        x = (int) (Math.random() * grid.getWidth());
                    }

                    obstacles.add(new Obstacle(x, row, speed, Obstacle.ObstacleType.CAR));
                }
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
                    player.restoreStartPosition();
                    player.resetScore();
                    return true;
                }
            }
        }
        return false; // Pas de collision
    }

    private void resetBothPlayerPositions() {
        player1.restoreStartPosition();
        if (duoMode && player2 != null) {
            player2.restoreStartPosition();
        }
    }
}