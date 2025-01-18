package com.frogger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Frog extends ImageView {
    public Frog() {
        this.setFitWidth(40);
        this.setFitHeight(40);
        this.setStyle("-fx-background-color: green;");
        this.setX(400);
        this.setY(550);
    }

    // Add movement methods here
}