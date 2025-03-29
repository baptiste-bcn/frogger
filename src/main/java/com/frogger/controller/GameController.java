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
    private GridPane obstacleLayer;

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

        // Initialize components after the game is set
        initializeGameComponents();
    }

    /**
     * ============================
     * INITIALIZE FXML SECTION
     * ============================
     **/

    @FXML
    public void initialize() {
        btnBack.setOnAction(event -> sceneController.showMenu());

        // Ajouter un écouteur pour s'assurer que la scène est attachée avant de
        // configurer les événements clavier
        grid.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPress);
                initializeGameComponents();

            }
        });
    }

    private void initializeGameComponents() {
        initializeGrid();
        initializeObstacles();
        initializePlayers();
        initializeKeyHandlers();
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

        // Positionner le joueur initialement
        GridPane.setColumnIndex(player1View, player1.getX());
        GridPane.setRowIndex(player1View, player1.getY());
        obstacleLayer.getChildren().add(player1View);

        if (isDuoMode) {
            Player player2 = game.getPlayer2();
            player2View = new Region();
            player2View.getStyleClass().add("player2");

            // Positionner le joueur 2
            GridPane.setColumnIndex(player2View, player2.getX());
            GridPane.setRowIndex(player2View, player2.getY());
            obstacleLayer.getChildren().add(player2View);
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
        // Gérer les collisions pour le joueur 1
        boolean collisionOccurred = game.handleCollision(game.getPlayer1());
        updatePlayerView();

        if (collisionOccurred) {
            updateScore();
        }

        // Gérer les collisions pour le joueur 2 si le mode duo est activé
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

    private void initializeKeyHandlers() {
        grid.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPress);
            }
        });
    }

    private void handleKeyPress(KeyEvent event) {

        Player player1 = game.getPlayer1();
        player1.savePreviousPosition();

        // Déplacement du joueur 1
        switch (event.getCode()) {
            case Z: // Haut
                player1.moveUp();
                break;
            case Q: // Gauche
                player1.moveLeft();
                break;
            case S: // Bas
                player1.moveDown(game.getGrid().getHeight());
                break;
            case D: // Droite
                player1.moveRight(game.getGrid().getWidth());
                break;
            default:
                break;
        }

        checkCollisions();

        // Déplacement du joueur 2 si le mode duo est activé

        if (isDuoMode) {
            Player player2 = game.getPlayer2();
            player2.savePreviousPosition();

            switch (event.getCode()) {
                case UP: // Haut
                    player2.moveUp();
                    break;
                case LEFT: // Gauche
                    player2.moveLeft();
                    break;
                case DOWN: // Bas
                    player2.moveDown(game.getGrid().getHeight());
                    break;
                case RIGHT: // Droite
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

    /**
     * ============================
     * OBSTACLES SECTION
     * ============================
     **/

    private void initializeObstacles() {
        // Add column constraints to obstacleLayer
        for (int col = 0; col < game.getGrid().getWidth(); col++) {
            obstacleLayer.getColumnConstraints().add(new ColumnConstraints(50)); // Set column width to 50
        }

        for (int row = 0; row < game.getGrid().getHeight(); row++) {
            obstacleLayer.getRowConstraints().add(new RowConstraints(50)); // Set row height to 50
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
                    // Mettre à jour les obstacles
                    game.updateObstacles();
                    updateObstacleView();

                    // Vérifier si le joueur a terminé
                    if (game.hasFinished(game.getPlayer1())) {
                        updateScore();
                        resetGameView();
                    }
                    if (isDuoMode && game.hasFinished(game.getPlayer2())) {
                        updateScore();
                    }

                    // Vérifier les collisions
                    checkCollisions();

                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }

    private void resetGameView() {
        // Réinitialiser le modèle
        game.resetGame();

        // Réinitialiser la grille principale
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        initializeGrid();

        // Réinitialiser la couche des obstacles
        obstacleLayer.getChildren().clear();
        obstacleLayer.getColumnConstraints().clear();
        obstacleLayer.getRowConstraints().clear();
        initializeObstacles();

        // Réinitialiser la vue du joueur
        initializePlayers(); // Recrée les vues des joueurs
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
