package com.frogger;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController {
    private final Stage stage;
    private final Canvas canvas;
    private final Grille grille;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final boolean isMultiplayer;

    public GameController(Stage stage, boolean isMultiplayer, int width, int height, int tileSize) {
        this.stage = stage;
        this.canvas = new Canvas(500, 500); // Initial canvas size
        this.grille = new Grille(canvas, width, height, tileSize);
        this.joueur1 = new Joueur(grille, Color.GREEN);
        this.joueur2 = isMultiplayer ? new Joueur(grille, Color.BLUE) : null;
        this.isMultiplayer = isMultiplayer;
    }

    public void lancerJeu() {
        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMenu());
        root.setTop(backButton);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::handleKeyPress);

        stage.setTitle("Frogger");
        stage.setScene(scene);
        stage.show();

        stage.sizeToScene();
        stage.requestFocus();
        mettreAJourAffichage();
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case Z -> joueur1.moveTop();
            case S -> joueur1.moveBottom();
            case Q -> joueur1.moveLeft();
            case D -> joueur1.moveRight();
            case UP -> {
                if (isMultiplayer && joueur2 != null)
                    joueur2.moveTop();
            }
            case DOWN -> {
                if (isMultiplayer && joueur2 != null)
                    joueur2.moveBottom();
            }
            case LEFT -> {
                if (isMultiplayer && joueur2 != null)
                    joueur2.moveLeft();
            }
            case RIGHT -> {
                if (isMultiplayer && joueur2 != null)
                    joueur2.moveRight();
            }
        }
        mettreAJourAffichage();
    }

    private void mettreAJourAffichage() {
        grille.dessinerGrille();
        joueur1.dessiner(canvas.getGraphicsContext2D(), grille.getTileSize());
        if (isMultiplayer) {
            joueur2.dessiner(canvas.getGraphicsContext2D(), grille.getTileSize());
        }
    }

    private void showMenu() {
        MenuController menu = new MenuController(stage);
        menu.showMenu();
    }
}