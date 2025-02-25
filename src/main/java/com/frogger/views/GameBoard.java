package com.frogger.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBoard {
    private Pane gamePane;
    private static final int TILE_SIZE = 40;
    private static final int ROWS = 15;
    private static final int COLS = 20;

    public GameBoard(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void createBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setX(j * TILE_SIZE);
                tile.setY(i * TILE_SIZE);

                // Determine tile type based on position
                TileType tileType = determineTileType(i);
                Color color = getTileColor(tileType);

                tile.setFill(color);
                tile.setStroke(Color.BLACK);
                tile.setStrokeWidth(0.5);

                gamePane.getChildren().add(tile);
            }
        }
    }

    private TileType determineTileType(int row) {
        if (row == 0)
            return TileType.GOAL;
        if (row == ROWS - 1)
            return TileType.START;
        if (row % 5 == 0)
            return TileType.SAFE;
        if (row < ROWS / 2)
            return TileType.WATER;
        return TileType.ROAD;
    }

    private Color getTileColor(TileType type) {
        switch (type) {
            case GOAL:
                return Color.DARKGREEN;
            case START:
                return Color.LIGHTGREEN;
            case SAFE:
                return Color.GREEN;
            case WATER:
                return Color.BLUE;
            case ROAD:
                return Color.GRAY;
            default:
                return Color.WHITE;
        }
    }

    enum TileType {
        START, GOAL, SAFE, ROAD, WATER
    }
}