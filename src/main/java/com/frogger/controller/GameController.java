package com.frogger.controller;

import com.frogger.model.Game;
import com.frogger.model.Player;
import com.frogger.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class GameController {

    /**
     * ============================
     * FXML ELEMENTS SECTION
     * ============================
     **/

    @FXML
    private Label lblScorePlayer1;

    @FXML
    private Label lblScorePlayer2;

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

    private boolean isKeyPressed;
    private KeyEvent lastKeyPressed;

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

        updateScore(); // Met à jour le score après le déplacement

        if (game.handleCollision(player1)) {
            updateScore();
        }

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

            if (playersCollide(player1, player2)) {
                player1.restorePreviousPosition();
                player2.restorePreviousPosition();
            }

            if (game.handleCollision(player2)) {
                updateScore();
            }
        }

        gameView.updatePlayerView(game.getPlayer1(), game.getPlayer2(), isDuoMode);
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
                    if (isDuoMode && game.hasFinished(game.getPlayer2())) {
                        updateScore();
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
