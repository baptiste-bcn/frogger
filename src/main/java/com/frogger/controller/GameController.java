package com.frogger.controller;

import com.frogger.model.Grille;
import com.frogger.model.Joueur;
import com.frogger.model.Obstacle;
import com.frogger.model.Route;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameController {
    private final Stage stage;
    private final Canvas canvas;
    private final Grille grille;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final boolean isMultiplayer;
    private final List<Obstacle> obstacles;
    private final List<Route> routes;
    private Set<KeyCode> pressedKeys = new HashSet<>();

    public GameController(Stage stage, boolean isMultiplayer, int width, int height, int tileSize) {
        this.stage = stage;
        this.canvas = new Canvas(width * tileSize, height * tileSize);
        this.grille = new Grille(canvas, width, height, tileSize);
        this.joueur1 = new Joueur(grille, Color.GREEN);
        this.joueur2 = isMultiplayer ? new Joueur(grille, Color.BLUE) : null;
        this.isMultiplayer = isMultiplayer;
        this.obstacles = new ArrayList<>();
        this.routes = new ArrayList<>();

        initializeObstacles();
    }

    private void initializeObstacles() {
        Random random = new Random();

        // Ajout de routes normales
        for (int i = 5; i < grille.getHeight() - 3; i += 3) {
            routes.add(new Route(grille, i, grille.getTileSize()));
        }

        // Ajout d'obstacles mobiles (voitures)
        obstacles.add(new Obstacle(grille, 2, 5, 3, 1, Color.RED, false)); // Voiture
        obstacles.add(new Obstacle(grille, 2, 11, 3, -1, Color.RED, false)); // Voiture

        // Ajout d'une route "eau" avec troncs flottants
        obstacles.add(new Obstacle(grille, 8, 8, 2, 1, Color.BROWN, false)); // Tronc
        obstacles.add(new Obstacle(grille, 2, 8, 2, 1, Color.BROWN, false)); // Tronc

        // Génération aléatoire d'arbres
        int nombreArbres = random.nextInt(15) + 25; // Entre 15 et 25 arbres

        for (int i = 0; i < nombreArbres; i++) {
            int x, y;
            boolean positionValide;

            do {
                x = random.nextInt(grille.getWidth()); // Position aléatoire en X
                y = random.nextInt(grille.getHeight()); // Position aléatoire en Y
                positionValide = true;

                for (Route route : routes) {
                    if (route.getY() == y) {
                        positionValide = false;
                        break;
                    }
                }
                for (Obstacle obstacle : obstacles) {
                    if (obstacle.getX() == x && obstacle.getY() == y) {
                        positionValide = false;
                        break;
                    }
                    if (y >= grille.getHeight() - 2) {
                        positionValide = false;
                        break;
                    }
                }
            } while (!positionValide);

            obstacles.add(new Obstacle(grille, x, y, 1, 0, Color.GREEN, true)); // Arbre statique
        }
    }

    private void handleKeyPress(KeyEvent event) {
        if (pressedKeys.add(event.getCode())) {
            // La touche vient d'être pressée
            switch (event.getCode()) {
                case Z -> joueur1.moveTop();
                case S -> joueur1.moveBottom();
                case Q -> joueur1.moveLeft();
                case D -> joueur1.moveRight();
                case UP -> {
                    if (isMultiplayer)
                        joueur2.moveTop();
                }
                case DOWN -> {
                    if (isMultiplayer)
                        joueur2.moveBottom();
                }
                case LEFT -> {
                    if (isMultiplayer)
                        joueur2.moveLeft();
                }
                case RIGHT -> {
                    if (isMultiplayer)
                        joueur2.moveRight();
                }
                default -> {
                }
            }
            checkCollisions();
            mettreAJourAffichage();
        }
        event.consume();
    }

    private void handleKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
        event.consume();
    }

    private void initializeKeyHandlers(Scene scene) {
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyReleased);
    }

    private void checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            // Vérifie les collisions avec les obstacles mobiles (voitures, troncs, etc.)
            if (!obstacle.isStatic() && obstacle.collidesWith(joueur1)) {
                System.out.println("\uD83D\uDC80 Collision détectée ! Joueur mort !");
                resetPlayer(joueur1);
            }
            if (isMultiplayer && !obstacle.isStatic() && obstacle.collidesWith(joueur2)) {
                System.out.println("\uD83D\uDC80 Collision détectée ! Joueur 2 mort !");
                resetPlayer(joueur2);
            }

            // Vérifie les collisions avec les obstacles statiques (buissons)
            if (obstacle.isStatic()) {
                if (obstacle.isStatic() && obstacle.collidesWith(joueur1)) {
                    joueur1.annulerDeplacement();
                }

                if (isMultiplayer && obstacle.isStatic() && obstacle.collidesWith(joueur2)) {
                    joueur2.annulerDeplacement();
                }
            }
        }
    }

    private void resetPlayer(Joueur joueur) {
        joueur.setPosition(grille.getWidth() / 2, grille.getHeight() - 1); // Retour à la position initiale
        joueur.resetScore(); // Réinitialiser le score
        mettreAJourAffichage();
    }

    private void mettreAJourAffichage() {
        grille.dessinerGrille();
        for (Route route : routes) {
            route.draw(canvas.getGraphicsContext2D(), grille.getTileSize());
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas.getGraphicsContext2D(), grille.getTileSize());
        }
        joueur1.dessiner(canvas.getGraphicsContext2D(), grille.getTileSize());
        if (isMultiplayer) {
            joueur2.dessiner(canvas.getGraphicsContext2D(), grille.getTileSize());
        }

        // Draw the score
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + joueur1.getScore(), 10, 20);
        if (isMultiplayer) {
            gc.fillText("Score Joueur 2: " + joueur2.getScore(), 10, 40);
        }
    }

    private void startGameLoop() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            for (Route route : routes) {
                route.deplacerObstacles(); // Déplace les obstacles de la route
            }
            for (Obstacle obstacle : obstacles) {
                obstacle.move(); // Déplace les obstacles en continu
            }
            checkCollisions();
            mettreAJourAffichage();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Répète indéfiniment
        timeline.play();
    }

    public void lancerJeu() {
        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMenu());
        root.setTop(backButton);

        Scene scene = new Scene(root);
        initializeKeyHandlers(scene);

        stage.setTitle("Frogger");
        stage.setScene(scene);
        stage.show();

        startGameLoop(); // Lancer l'animation des obstacles
    }

    private void showMenu() {
        MenuController menu = new MenuController(stage);
        menu.showMenu();
    }
}
