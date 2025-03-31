package com.frogger.view;

import com.frogger.model.Game;
import com.frogger.model.Grid.RowType;
import com.frogger.model.Obstacle;
import com.frogger.model.Player;
import com.frogger.model.Grid;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Region;
import javafx.scene.Node;

public class GameView {
    private final GridPane grid;
    private final GridPane entityLayer;

    private Region player1View;
    private Region player2View;

    public GameView(GridPane grid, GridPane entityLayer) {
        this.grid = grid;
        this.entityLayer = entityLayer;
    }

    /**
     * ============================
     * INITIALIZE SECTION
     * ============================
     **/

    public void initializeGrid(Game game) {
        Grid gridModel = game.getGrid();

        for (int col = 0; col < gridModel.getWidth(); col++) {
            grid.getColumnConstraints().add(new ColumnConstraints(50));
        }
        for (int row = 0; row < gridModel.getHeight(); row++) {
            grid.getRowConstraints().add(new RowConstraints(50));
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

    public void initializePlayers(Player player1, Player player2, boolean isDuoMode) {
        player1View = new Region();
        player1View.getStyleClass().add("player");

        GridPane.setColumnIndex(player1View, player1.getX());
        GridPane.setRowIndex(player1View, player1.getY());
        entityLayer.getChildren().add(player1View);

        if (isDuoMode && player2 != null) {
            player2View = new Region();
            player2View.getStyleClass().add("player2");

            GridPane.setColumnIndex(player2View, player2.getX());
            GridPane.setRowIndex(player2View, player2.getY());
            entityLayer.getChildren().add(player2View);
        }
    }

    /**
     * ============================
     * UPDATE SECTION
     * ============================
     **/

    public void updatePlayerView(Player player1, Player player2, boolean isDuoMode) {
        if (player1View != null) {
            GridPane.setColumnIndex(player1View, player1.getX());
            GridPane.setRowIndex(player1View, player1.getY());
        }

        if (isDuoMode && player2 != null && player2View != null) {
            GridPane.setColumnIndex(player2View, player2.getX());
            GridPane.setRowIndex(player2View, player2.getY());
        }
    }

    public void updateObstacleView() {
        for (Node node : entityLayer.getChildren()) {
            if (node instanceof Region) {
                Region obstacleView = (Region) node;
                Object userData = obstacleView.getUserData();

                if (userData instanceof Obstacle) {
                    Obstacle obstacle = (Obstacle) userData;
                    GridPane.setColumnIndex(obstacleView, obstacle.getX());
                    GridPane.setRowIndex(obstacleView, obstacle.getY());
                }
            }
        }
    }

    /**
     * ============================
     * RESET SECTION
     * ============================
     **/

    public void resetGameView(Game game) {
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        initializeGrid(game);

        entityLayer.getChildren().clear();
        entityLayer.getColumnConstraints().clear();
        entityLayer.getRowConstraints().clear();
        initializeObstacles(game);

        initializePlayers(game.getPlayer1(), game.getPlayer2(), game.isDuoMode());
    }

}
