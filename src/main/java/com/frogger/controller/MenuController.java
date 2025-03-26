package com.frogger.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import view.MenuView;
import view.GameView;
import com.frogger.model.Game;
import com.frogger.model.Grille;
import com.frogger.model.Joueur;
import javafx.scene.paint.Color;

public class MenuController {
    private Stage stage;
    private GameController gameController;

    public MenuController(Stage stage) {
        this.stage = stage;
    }
    public void showMenu() {
        MenuView menuView = new MenuView(stage);
        menuView.setStartAction(() -> startGame(false));
        menuView.showMenu();
    }

    private void startGame(boolean isMultiplayer) {
        Grille grille = new Grille(15, 20);
        Game game = new Game(grille);
        Joueur joueur = new Joueur(grille, Color.GREEN);

        GameView gameView = new GameView(grille, 30, joueur);
        Group root = new Group();
        root.getChildren().add(gameView);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);

        gameController = new GameController(game, gameView);
        gameController.startGame();
    }
}