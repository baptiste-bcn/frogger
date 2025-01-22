package com.frogger;

import javafx.scene.image.ImageView;

public class Frog extends ImageView {
    public Frog() {
        this.setFitWidth(40);
        this.setFitHeight(40);
        this.setStyle("-fx-background-color: blue;");
        this.setX(400);
        this.setY(550);
    }

    public void moveUp() {
        this.setY(this.getY() - 40);
    }

    public void moveDown() {
        this.setY(this.getY() + 40);
    }

    public void moveLeft() {
        this.setX(this.getX() - 40);
    }

    public void moveRight() {
        this.setX(this.getX() + 40);
    }

    public void initializePosition() {
        this.setX(400);
        this.setY(0);
    }

    public double getWidth() {
        return this.getFitWidth();
    }
}