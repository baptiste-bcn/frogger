package com.frogger.controller;

import com.frogger.model.Game;
import com.frogger.model.Grid;
import com.frogger.model.Grid.RowType;
import com.frogger.model.Obstacle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.Node;

public class GameController {
    @FXML
    private GridPane grid;

    @FXML
    private GridPane obstacleLayer;

    @FXML
    private Button btnBack;

    private SceneController sceneController;
    private Game game = new Game();

    @FXML
    public void initialize() {
        btnBack.setOnAction(event -> sceneController.showMenu());

        initializeGrid();
        initializeObstacles();
        // Démarrer la boucle de jeu
        startGameLoop();
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void initializeGrid() {
        Grid gridModel = game.getGrid();

        // Add row and column constraints to the grid
        for (int col = 0; col < gridModel.getWidth(); col++) {
            grid.getColumnConstraints().add(new ColumnConstraints(50)); // Set column width to 50
        }
        for (int row = 0; row < gridModel.getHeight(); row++) {
            grid.getRowConstraints().add(new RowConstraints(50)); // Set row height to 50
        }

        for (int row = 0; row < gridModel.getHeight(); row++) {
            RowType rowType = gridModel.getRowType(row);
            for (int col = 0; col < gridModel.getWidth(); col++) {
                Region cell = new Region();
                if (rowType == RowType.SAFE) {
                    cell.getStyleClass().addAll("grid-cell", "safe-cell");
                } else if (rowType == RowType.ROAD) {
                    cell.getStyleClass().addAll("grid-cell", "road-cell");
                }
                grid.add(cell, col, row);
            }
        }
    }

    private void initializeObstacles() {
        // Add column constraints to obstacleLayer
        for (int col = 0; col < game.getGrid().getWidth(); col++) {
            obstacleLayer.getColumnConstraints().add(new ColumnConstraints(50)); // Set column width to 50
        }

        for (Obstacle obstacle : game.getObstacles()) {
            Region obstacleView = new Region();
            if (obstacle.getType() == Obstacle.ObstacleType.TREE) {
                obstacleView.getStyleClass().add("tree");
            } else if (obstacle.getType() == Obstacle.ObstacleType.CAR) {
                obstacleView.getStyleClass().add("car");
            }

            // Ajouter l'obstacle dans la bonne cellule
            GridPane.setColumnIndex(obstacleView, obstacle.getX());
            GridPane.setRowIndex(obstacleView, obstacle.getY());
            obstacleLayer.getChildren().add(obstacleView);

            // Associer l'obstacleView à l'obstacle pour mise à jour ultérieure
            obstacleView.setUserData(obstacle);
        }
    }

    private void updateObstacleView() {
        for (Node node : obstacleLayer.getChildren()) {
            if (node instanceof Region) {
                Region obstacleView = (Region) node;
                Obstacle obstacle = (Obstacle) obstacleView.getUserData();
                GridPane.setColumnIndex(obstacleView, obstacle.getX());
                GridPane.setRowIndex(obstacleView, obstacle.getY());
            }
        }
    }

    private void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100_000_000) {
                    game.updateObstacles();
                    updateObstacleView();
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }
}
