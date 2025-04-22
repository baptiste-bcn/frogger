package com.frogger.controller;

import com.frogger.model.Game;
import com.frogger.model.Player;
import com.frogger.view.GameView;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class GameController {

    /**
     * ============================
     * FXML ELEMENTS SECTION
     * ============================
     **/

    @FXML
    private Button lblScorePlayer1;

    @FXML
    private Button lblScorePlayer2;

    @FXML
    private GridPane grid;

    @FXML
    private GridPane entityLayer;

    @FXML
    private Button btnBack;

    @FXML
    private Button lblTimer;

    /**
     * ============================
     * GAMECONTROLLER ATTRIBUTES SECTION
     * ============================
     **/

    private SceneController sceneController;
    private GameView gameView;

    private boolean isDuoMode;
    private Game game;

    private boolean isKeyPressed;
    private KeyEvent lastKeyPressed;

    private long startTime;
    private AnimationTimer timer;

    public void setDuoMode(boolean isDuoMode) {
        this.isDuoMode = isDuoMode;
        this.game = new Game(isDuoMode);
        this.gameView = new GameView(grid, entityLayer);

        lblScorePlayer2.setVisible(isDuoMode);

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
                newScene.setOnKeyReleased(this::handleKeyRelease);

                if (game != null) {
                    setupGameComponents();
                }

            }
        });
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                lblTimer.setText("Durée de la partie : " + elapsedTime + "s");
            }
        };
        timer.start();
    }

    private void resetTimer() {
        if (timer != null) {
            timer.stop();
        }
        lblTimer.setText("Durée de la partie : 0s");
        startTimer();
    }

    private void setupGameComponents() {
        resetTimer();
        gameView.initializeGrid(game);
        gameView.initializeObstacles(game);
        gameView.initializePlayers(game.getPlayer1(), game.getPlayer2(), isDuoMode);

        lblTimer.setMouseTransparent(true);
        lblTimer.setFocusTraversable(false);
        lblScorePlayer1.setMouseTransparent(true);
        lblScorePlayer1.setFocusTraversable(false);
        lblScorePlayer2.setMouseTransparent(true);
        lblScorePlayer2.setFocusTraversable(false);

        startGameLoop();
    }

    /**
     * ============================
     * KEY HANDLER SECTION
     * ============================
     **/
    private boolean playersCollide(Player player1, Player player2) {
        return player1.getX() == player2.getX() && player1.getY() == player2.getY();
    }

    private void handleKeyPress(KeyEvent event) {
        if (isKeyPressed && lastKeyPressed != null && event.getCode() == lastKeyPressed.getCode()) {
            return;
        }

        isKeyPressed = true;
        lastKeyPressed = event;

        handlePlayerMove(game.getPlayer1(), event.getCode(), true);

        if (game.handleCollision(game.getPlayer1())) {
            updateScore();
        }

        if (isDuoMode) {
            handlePlayerMove(game.getPlayer2(), event.getCode(), false);

            if (playersCollide(game.getPlayer1(), game.getPlayer2())) {
                game.getPlayer1().restorePreviousPosition();
                game.getPlayer2().restorePreviousPosition();
            }

            if (game.handleCollision(game.getPlayer2())) {
                updateScore();
            }
        }

        gameView.updatePlayerView(game.getPlayer1(), game.getPlayer2(), isDuoMode);
    }

    private void handlePlayerMove(Player player, KeyCode code, boolean isFirstPlayer) {
        player.savePreviousPosition();

        if (isFirstPlayer) {
            switch (code) {
                case Z -> player.moveUp();
                case Q -> player.moveLeft();
                case S -> player.moveDown(game.getGrid().getHeight());
                case D -> player.moveRight(game.getGrid().getWidth());
            }
        } else {
            switch (code) {
                case UP -> player.moveUp();
                case LEFT -> player.moveLeft();
                case DOWN -> player.moveDown(game.getGrid().getHeight());
                case RIGHT -> player.moveRight(game.getGrid().getWidth());
            }
        }
    }


    private void handleKeyRelease(KeyEvent event) {
        isKeyPressed = false;
    }

    private void updateScore() {
        lblScorePlayer1.setText("Score Joueur 1 : " + game.getPlayer1().getScore());
        if (isDuoMode) {
            lblScorePlayer2.setVisible(true);
            lblScorePlayer2.setText("Score Joueur 2 : " + game.getPlayer2().getScore());
        } else {
            lblScorePlayer2.setVisible(false);
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
                    gameView.updateObstacleView();

                    if (game.hasFinished(game.getPlayer1())) {
                        updateScore();
                        game.resetGame();
                        gameView.resetGameView(game);
                    }
                    if (game.hasFinished(game.getPlayer2())) {
                        updateScore();
                        game.resetGame();
                        gameView.resetGameView(game);
                    }

                    if (game.handleCollision(game.getPlayer1())) {
                        updateScore();
                    }
                    if (isDuoMode && game.handleCollision(game.getPlayer2())) {
                        updateScore();
                    }
                    gameView.updatePlayerView(game.getPlayer1(), game.getPlayer2(), isDuoMode);

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
