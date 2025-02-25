package com.frogger;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class GameController {
    private final Stage stage;
    private final Canvas canvas;
    private final Grille grille;
    private final Joueur joueur;

    public GameController(Stage stage) {
        this.stage = stage;
        this.canvas = new Canvas(500, 500); // Taille du canvas initiale
        this.grille = new Grille(canvas);
        this.joueur = new Joueur(grille);
    }

    public void lancerJeu() {
        Pane root = new Pane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::handleKeyPress);

        stage.setTitle("Frogger");
        stage.setScene(scene);
        stage.show();

        stage.requestFocus();
        mettreAJourAffichage();
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> joueur.moveTop();
            case DOWN -> joueur.moveBottom();
            case LEFT -> joueur.moveLeft();
            case RIGHT -> joueur.moveRight();
        }
        mettreAJourAffichage();
    }

    private void mettreAJourAffichage() {
        grille.dessinerGrille();
        joueur.dessiner(canvas.getGraphicsContext2D(), grille.getTileSize());
    }
}
