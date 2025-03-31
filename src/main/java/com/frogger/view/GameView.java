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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class GameView {
    private final GridPane grid;
    private final GridPane entityLayer;

    private Region player1View;
    private Region player2View;

    private static final Image GRASS_TILE_IMAGE = new Image(
            GameView.class.getResourceAsStream("/images/GrassTile.png"));
    private static final Image ROAD_TILE_IMAGE = new Image(GameView.class.getResourceAsStream("/images/RoadTile.png"));
    private static final Image BLUE_CAR_IMAGE = new Image(
            GameView.class.getResourceAsStream("/images/BlueCar.png"));
    private static final Image TREE_IMAGE = new Image(GameView.class.getResourceAsStream("/images/Tree.png"));

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
                ImageView cell = new ImageView();
                if (rowType == RowType.SAFE) {
                    cell.setImage(GRASS_TILE_IMAGE);
                } else if (rowType == RowType.ROAD) {
                    cell.setImage(ROAD_TILE_IMAGE);
                }
                cell.setFitWidth(50);
                cell.setFitHeight(50);
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
            ImageView obstacleView = new ImageView();
            if (obstacle.getType() == Obstacle.ObstacleType.TREE) {
                obstacleView = new ImageView(TREE_IMAGE);
                obstacleView.getStyleClass().add("tree");
                obstacleView.setFitWidth(50);
                obstacleView.setFitHeight(50);
            } else if (obstacle.getType() == Obstacle.ObstacleType.CAR) {
                obstacleView = new ImageView(BLUE_CAR_IMAGE);
                obstacleView.getStyleClass().add("car");
                obstacleView.setFitWidth(60);
                obstacleView.setFitHeight(60);
            }

            if (obstacle.getType() == Obstacle.ObstacleType.CAR) {
                if (obstacle.getSpeed() == 1) {
                    obstacleView.setRotate(90);
                } else if (obstacle.getSpeed() == -1) {
                    obstacleView.setRotate(-90);
                }
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
            if (node instanceof ImageView) {
                ImageView obstacleView = (ImageView) node;
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
