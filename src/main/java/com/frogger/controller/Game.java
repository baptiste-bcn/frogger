package com.frogger.controller;

import com.frogger.models.Frog;
import com.frogger.views.GameBoard;
import com.frogger.views.ObstacleManager;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game {
    private Stage stage;
    private Pane gamePane;
    private Scene gameScene;
    private Frog player;
    private GameBoard board;
    private ObstacleManager obstacleManager;
    private ScoreManager scoreManager;
    private boolean gameRunning = false;
    private AnimationTimer gameLoop;

    public Game(Stage stage) {
        this.stage = stage;
        initializeGame();
    }

    private void initializeGame() {
        gamePane = new Pane();
        gameScene = new Scene(gamePane, 800, 600);
        board = new GameBoard(gamePane);

        player = new Frog();
        obstacleManager = new ObstacleManager(gamePane);
        scoreManager = new ScoreManager();

        // Missing this line:
        scoreManager.addToPane(gamePane);

        setupControls();
        setupGameLoop();
    }

    public void start() {
        board.createBoard();
        player.initializePosition();
        gamePane.getChildren().add(player);
        obstacleManager.createInitialObstacles();

        stage.setScene(gameScene);
        stage.show();

        gameRunning = true;
        gameLoop.start();
    }

    private void setupControls() {
        gameScene.setOnKeyPressed(event -> {
            if (!gameRunning)
                return;

            switch (event.getCode()) {
                case UP:
                    player.moveUp();
                    break;
                case DOWN:
                    player.moveDown();
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case RIGHT:
                    player.moveRight();
                    break;
                default:
                    break;
            }

            checkCollisions();
            checkWinCondition();
        });
    }

    private void setupGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
            }
        };
    }

    private void updateGame() {
        obstacleManager.updateObstacles();
        checkCollisions();
        scoreManager.updateScore();
    }

    private void checkCollisions() {
        if (obstacleManager.checkCollision(player)) {
            System.out.println("Collision at: " + player.getX() + "," + player.getY());
            handleCollision();
        }
    }

    private void checkWinCondition() {
        if (player.getY() <= 0) {
            scoreManager.addPoints(1000);
            resetLevel();
        }
    }

    private void handleCollision() {
        player.initializePosition();
        scoreManager.loseLife();

        if (scoreManager.getLives() <= 0) {
            endGame();
        }
    }

    private void resetLevel() {
        player.initializePosition();
    }

    private void endGame() {
        gameRunning = false;
        gameLoop.stop();
        // Show game over screen with score
    }

}