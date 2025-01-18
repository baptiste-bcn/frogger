package com.frogger;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game {
    private Stage stage;

    public Game(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        // Add game elements here
        Frog frog = new Frog();
        root.getChildren().add(frog);

        stage.setScene(scene);
        stage.show();
    }
}