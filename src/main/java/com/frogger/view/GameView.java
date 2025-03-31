package com.frogger.view;

import com.frogger.model.Game;
import com.frogger.model.Grid.RowType;
import com.frogger.model.Obstacle;
import com.frogger.model.Player;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Region;

public class GameView {
    private final GridPane grid;
    private final GridPane entityLayer;

    public GameView(GridPane grid, GridPane entityLayer) {
        this.grid = grid;
        this.entityLayer = entityLayer;
    }

    public void initializeGrid(Game game) {
        for (int col = 0; col < game.getGrid().getWidth(); col++) {
            grid.getColumnConstraints().add(new ColumnConstraints(50));
        }
        for (int row = 0; row < game.getGrid().getHeight(); row++) {
            grid.getRowConstraints().add(new RowConstraints(50));
        }

        for (int row = 0; row < game.getGrid().getHeight(); row++) {
            RowType rowType = game.getGrid().getRowType(row);
            for (int col = 0; col < game.getGrid().getWidth(); col++) {
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

    public void initializeObstacles(Game game) {
        for (int col = 0; col < game.getGrid().getWidth(); col++) {
            entityLayer.getColumnConstraints().add(new ColumnConstraints(50));
        }

        for (int row = 0; row < game.getGrid().getHeight(); row++) {
            entityLayer.getRowConstraints().add(new RowConstraints(50));
        }

        for (Obstacle obstacle : game.getObstacles()) {
            Region obstacleView = new Region();
            if (obstacle.getType() == Obstacle.ObstacleType.TREE) {
                obstacleView.getStyleClass().add("tree");
            } else if (obstacle.getType() == Obstacle.ObstacleType.CAR) {
                obstacleView.getStyleClass().add("car");
            }

            GridPane.setColumnIndex(obstacleView, obstacle.getX());
            GridPane.setRowIndex(obstacleView, obstacle.getY());
            entityLayer.getChildren().add(obstacleView);

            obstacleView.setUserData(obstacle);
        }
    }

    public void initializePlayers(Player player1, Player player2, Region player1View, Region player2View,
            boolean isDuoMode) {
        GridPane.setColumnIndex(player1View, player1.getX());
        GridPane.setRowIndex(player1View, player1.getY());
        entityLayer.getChildren().add(player1View);

        if (isDuoMode && player2 != null) {
            GridPane.setColumnIndex(player2View, player2.getX());
            GridPane.setRowIndex(player2View, player2.getY());
            entityLayer.getChildren().add(player2View);
        }
    }

}
