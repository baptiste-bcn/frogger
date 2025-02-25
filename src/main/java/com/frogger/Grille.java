package com.frogger;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grille {
    private int TILE_SIZE = 50;
    private int GRID_WIDTH = 10;
    private int GRID_HEIGHT = 10;
    private Canvas canvas;

    public Grille(Canvas canvas, int width, int height, int tileSize) {
        this.canvas = canvas;
        this.GRID_WIDTH = width;
        this.GRID_HEIGHT = height;
        this.TILE_SIZE = tileSize;

        // Ajuster la taille du canvas en fonction de la taille de la grille
        canvas.setWidth(width * tileSize);
        canvas.setHeight(height * tileSize);
    }

    public Grille(Canvas canvas) {
        this.canvas = canvas;
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
