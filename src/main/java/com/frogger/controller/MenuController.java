package com.frogger.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuController {
    private final Stage stage;

    public MenuController(Stage stage) {
        this.stage = stage;
    }

    public void showMenu() {
        VBox menuLayout = new VBox(25); // Espacement amélioré
        menuLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Froggy Road");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setTextFill(Color.DARKGREEN);

        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(4.0);
        shadow.setColor(Color.gray(0.3));
        title.setEffect(shadow);

        Button soloButton = createStyledButton("Jouer Solo");
        Button multiplayerButton = createStyledButton("Mode Multijoueur");
        Button quitButton = createStyledButton("Quitter");

        soloButton.setOnAction(e -> startGame(false));
        multiplayerButton.setOnAction(e -> startGame(true));
        quitButton.setOnAction(e -> stage.close());

        menuLayout.getChildren().addAll(title, soloButton, multiplayerButton, quitButton);

        Scene menuScene = new Scene(menuLayout, 500, 400);
        stage.setScene(menuScene);
        stage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setTextFill(Color.WHITE);
        button.setStyle(
                "-fx-background-color: #228B22; " + // Vert foncé
                        "-fx-background-radius: 15px; " + // Bords arrondis
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15px;");

        // Effet au survol
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #32CD32; " + // Vert clair
                        "-fx-background-radius: 15px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15px;"));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #228B22; " +
                        "-fx-background-radius: 15px; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15px;"));

        return button;
    }

    private void startGame(boolean isMultiplayer) {
        GameController gameController = new GameController(stage, isMultiplayer, 15, 20, 30);
        gameController.lancerJeu();
    }
}