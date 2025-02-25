package com.frogger;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grille {
    private final int TILE_SIZE;
    private final int GRID_WIDTH;
    private final int GRID_HEIGHT;
    private final Canvas canvas;

    public Grille(Canvas canvas, int width, int height, int tileSize) {
        this.canvas = canvas;
        this.GRID_WIDTH = width;
        this.GRID_HEIGHT = height;
        this.TILE_SIZE = tileSize;

        canvas.setWidth(width * tileSize);
        canvas.setHeight(height * tileSize);
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
