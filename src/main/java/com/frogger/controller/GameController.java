package com.frogger.controller;

import com.frogger.model.Game;
import com.frogger.model.Player;
import com.frogger.model.Obstacle;
import com.frogger.view.GameView;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
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
    private GameView gameView;

    private boolean isDuoMode;
    private Game game;

    public void setDuoMode(boolean isDuoMode) {
        this.isDuoMode = isDuoMode;
        this.game = new Game(isDuoMode);
        this.gameView = new GameView(grid, entityLayer);

        setupGameComponents();
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
                    setupGameComponents();
                } else {
                    System.out.println("Game is not initialized yet.");
                }

            }
        });
    }

    private void setupGameComponents() {
        gameView.initializeGrid(game);
        gameView.initializeObstacles(game);
        gameView.initializePlayers(game.getPlayer1(), game.getPlayer2(), isDuoMode);
        startGameLoop();
    }

    private void checkCollisions() {
        boolean collisionOccurred = game.handleCollision(game.getPlayer1());
        gameView.updatePlayerView(game.getPlayer1(), game.getPlayer2(), isDuoMode);

        if (collisionOccurred) {
            updateScore();
        }

        if (isDuoMode) {
            collisionOccurred = game.handleCollision(game.getPlayer2());
            gameView.updatePlayerView(game.getPlayer1(), game.getPlayer2(), isDuoMode);

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
                    gameView.updateObstacleView();

                    if (game.hasFinished(game.getPlayer1())) {
                        updateScore();
                        game.resetGame();
                        gameView.resetGameView(game);
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

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
