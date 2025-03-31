package com.frogger.controller;

import com.frogger.model.Game;
import com.frogger.model.Player;
import com.frogger.model.Grid;
import com.frogger.model.Grid.RowType;
import com.frogger.model.Obstacle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class GameController {

    /**
     * ============================
     * FXML ELEMENTS SECTION
     * ============================
     **/

    @FXML
    private Label lblScore;

    @FXML
    private GridPane grid;

    @FXML
    private GridPane entityLayer;

    @FXML
    private Button btnBack;

    /**
     * ============================
     * GAMECONTROLLER ATTRIBUTES SECTION
     * ============================
     **/

    private SceneController sceneController;

    private boolean isDuoMode;
    private Game game;

    private Region player1View;
    private Region player2View;

    public void setDuoMode(boolean isDuoMode) {
        this.isDuoMode = isDuoMode;
        this.game = new Game(isDuoMode);

        initializeGameComponents();
    }

    /**
     * ============================
     * INITIALIZE FXML SECTION
     * ============================
     **/

    @FXML
    public void initialize() {
        grid.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                btnBack.setOnAction(event -> sceneController.showMenu());

                newScene.setOnKeyPressed(this::handleKeyPress);

                if (game != null) {
                    initializeGameComponents();
                } else {
                    System.out.println("Game is not initialized yet.");
                }
            }
        });
    }

    private void initializeGameComponents() {
        initializeGrid();
        initializeObstacles();
        initializePlayers();
        startGameLoop();
    }

    /**
     * ============================
     * PLAYER SECTION
     * ============================
     **/
    private void initializePlayers() {
        Player player1 = game.getPlayer1();
        player1View = new Region();
        player1View.getStyleClass().add("player");

        GridPane.setColumnIndex(player1View, player1.getX());
        GridPane.setRowIndex(player1View, player1.getY());
        entityLayer.getChildren().add(player1View);

        if (isDuoMode) {
            Player player2 = game.getPlayer2();
            player2View = new Region();
            player2View.getStyleClass().add("player2");

            GridPane.setColumnIndex(player2View, player2.getX());
            GridPane.setRowIndex(player2View, player2.getY());
            entityLayer.getChildren().add(player2View);
        }
    }

    private void updatePlayerView() {
        Player player1 = game.getPlayer1();
        if (player1View != null) {
            GridPane.setColumnIndex(player1View, player1.getX());
            GridPane.setRowIndex(player1View, player1.getY());
        }

        if (isDuoMode) {
            Player player2 = game.getPlayer2();
            if (player2View != null) {
                GridPane.setColumnIndex(player2View, player2.getX());
                GridPane.setRowIndex(player2View, player2.getY());
            }
        }
    }

    private void checkCollisions() {
        boolean collisionOccurred = game.handleCollision(game.getPlayer1());
        updatePlayerView();

        if (collisionOccurred) {
            updateScore();
        }

        if (isDuoMode) {
            collisionOccurred = game.handleCollision(game.getPlayer2());
            updatePlayerView();

            if (collisionOccurred) {
                updateScore();
            }
        }
    }

    /**
     * ============================
     * KEY HANDLER SECTION
     * ============================
     **/

    private void handleKeyPress(KeyEvent event) {

        Player player1 = game.getPlayer1();
        player1.savePreviousPosition();

        switch (event.getCode()) {
            case Z:
                player1.moveUp();
                break;
            case Q:
                player1.moveLeft();
                break;
            case S:
                player1.moveDown(game.getGrid().getHeight());
                break;
            case D:
                player1.moveRight(game.getGrid().getWidth());
                break;
            default:
                break;
        }

        checkCollisions();

        if (isDuoMode) {
            Player player2 = game.getPlayer2();
            player2.savePreviousPosition();

            switch (event.getCode()) {
                case UP:
                    player2.moveUp();
                    break;
                case LEFT:
                    player2.moveLeft();
                    break;
                case DOWN:
                    player2.moveDown(game.getGrid().getHeight());
                    break;
                case RIGHT:
                    player2.moveRight(game.getGrid().getWidth());
                    break;
                default:
                    break;
            }

            checkCollisions();
        }

    }

    private void updateScore() {
        lblScore.setText("Player 1 Score: " + game.getPlayer1().getScore() +
                (isDuoMode ? " | Player 2 Score: " + game.getPlayer2().getScore() : ""));
    }

    /**
     * ============================
     * GRID SECTION
     * ============================
     **/

    private void initializeGrid() {
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

    /**
     * ============================
     * OBSTACLES SECTION
     * ============================
     **/

    private void initializeObstacles() {
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

    private void updateObstacleView() {
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
     * GAME LOOP SECTION
     * ============================
     **/

    private void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 100_000_000) {
                    game.updateObstacles();
                    updateObstacleView();

                    if (game.hasFinished(game.getPlayer1())) {
                        updateScore();
                        resetGameView();
                    }
                    if (isDuoMode && game.hasFinished(game.getPlayer2())) {
                        updateScore();
                    }

                    checkCollisions();

                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }

    private void resetGameView() {
        game.resetGame();

        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        initializeGrid();

        entityLayer.getChildren().clear();
        entityLayer.getColumnConstraints().clear();
        entityLayer.getRowConstraints().clear();
        initializeObstacles();

        initializePlayers();
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
