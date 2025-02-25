package com.frogger.models;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; // Add this import statement// Add this import statement

import java.util.Objects;

public class Frog extends ImageView {
    private static final int MOVE_DISTANCE = 40;
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;

    public Frog() {
        // Try to load an image if available
        try {
            setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ressources/images/frog.png"))));
        } catch (Exception e) {
            // Fallback to colored rectangle
            setFitWidth(40);
            setFitHeight(40);
            setStyle("-fx-background-color: green;");
        }

        initializePosition();
    }

    public void moveUp() {
        if (getY() - MOVE_DISTANCE >= 0) {
            setY(getY() - MOVE_DISTANCE);
        }
    }

    public void moveDown() {
        if (getY() + MOVE_DISTANCE + getFitHeight() <= BOARD_HEIGHT) {
            setY(getY() + MOVE_DISTANCE);
        }
    }

    public void moveLeft() {
        if (getX() - MOVE_DISTANCE >= 0) {
            setX(getX() - MOVE_DISTANCE);
        }
    }

    public void moveRight() {
        if (getX() + MOVE_DISTANCE + getFitWidth() <= BOARD_WIDTH) {
            setX(getX() + MOVE_DISTANCE);
        }
    }

    public void initializePosition() {
        setX((double) BOARD_WIDTH / 2 - getFitWidth() / 2);
        setY(BOARD_HEIGHT - getFitHeight() - MOVE_DISTANCE);
    }

    public boolean intersects(Bounds bounds) {
        return getBoundsInParent().intersects(bounds);
    }

}