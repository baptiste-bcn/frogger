package com.frogger.views;

import com.frogger.controller.Game;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu {
    private Stage stage;

    public Menu(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: green;");
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label logo = new Label("Frogger");
        logo.setFont(new Font("Arial", 40));
        logo.setTextFill(Color.WHITE);

        Button soloButton = new Button("Jouer Solo");
        soloButton.setOnAction(e -> startGame());

        Button duoButton = new Button("Mode Duo");
        // Add action for duoButton

        Button settingsButton = new Button("Paramètres");
        // Add action for settingsButton

        Button quitButton = new Button("Quitter");
        quitButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(logo, soloButton, duoButton, settingsButton, quitButton);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void startGame() {
        // Change to the game scene
        Game game = new Game(stage);
        game.start();
    }
}