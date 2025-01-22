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
        createBackground(root);
        Scene scene = new Scene(root, 800, 600);

        Frog frog = new Frog();
        root.getChildren().add(frog);

        stage.setScene(scene);
        stage.show();
    }

    private void createBackground(Pane root) {
        int tileSize = 40;
        int rows = 15;
        int cols = 20;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                javafx.scene.shape.Rectangle tile = new javafx.scene.shape.Rectangle(tileSize, tileSize);
                tile.setX(j * tileSize);
                tile.setY(i * tileSize);

                if (i % 2 == 0) {
                    tile.setFill(javafx.scene.paint.Color.GREEN); // Safe zones
                } else {
                    tile.setFill(javafx.scene.paint.Color.GRAY); // Car zones
                }

                root.getChildren().add(tile);
            }
        }
    }

}
