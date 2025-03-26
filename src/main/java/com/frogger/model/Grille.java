package com.frogger.model;

import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Grille {
    private final int TILE_SIZE;
    private final int GRID_WIDTH;
    private final int GRID_HEIGHT;
    private final Canvas canvas;
    private List<Route> routes = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();
    private Grille grille = this;

    public Grille(Canvas canvas, int width, int height, int tileSize) {
        this.canvas = canvas;
        this.GRID_WIDTH = width;
        this.GRID_HEIGHT = height;
        this.TILE_SIZE = tileSize;

        canvas.setWidth(width * tileSize);
        canvas.setHeight(height * tileSize);

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

    public void dessinerGrille() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.GRAY);
        for (int i = 0; i <= GRID_WIDTH; i++) {
            gc.strokeLine(i * TILE_SIZE, 0, i * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);
        }
        for (int i = 0; i <= GRID_HEIGHT; i++) {
            gc.strokeLine(0, i * TILE_SIZE, GRID_WIDTH * TILE_SIZE, i * TILE_SIZE);
        }

        // Dessiner les routes
        for (Route route : routes) {
            route.draw(gc, TILE_SIZE);
        }

        // Dessiner les obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(gc, TILE_SIZE);
        }
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getWidth() {
        return GRID_WIDTH;
    }

    public int getHeight() {
        return GRID_HEIGHT;
    }
}