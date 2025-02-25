package com.frogger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;

public class MenuController {
    private final Stage stage;

    public MenuController(Stage stage) {
        this.stage = stage;
    }

    public void showMenu() {
        // Layout principal
        VBox menuLayout = new VBox(25); // Espacement amélioré
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #98FB98, #2E8B57);"); // Dégradé vert

        // Titre du jeu
        Label title = new Label("FROGGER");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setTextFill(Color.DARKGREEN);

        // Effet d'ombre portée sur le titre
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(4.0);
        shadow.setColor(Color.gray(0.3));
        title.setEffect(shadow);

        // Création des boutons stylés
        Button soloButton = createStyledButton("Jouer Solo");
        Button multiplayerButton = createStyledButton("Mode Multijoueur");
        Button quitButton = createStyledButton("Quitter");

        // Actions des boutons
        soloButton.setOnAction(e -> startGame(false));
        multiplayerButton.setOnAction(e -> startGame(true));
        quitButton.setOnAction(e -> stage.close());

        // Ajout des éléments à la scène
        menuLayout.getChildren().addAll(title, soloButton, multiplayerButton, quitButton);

        // Création de la scène
        Scene menuScene = new Scene(menuLayout, 500, 400);
        stage.setScene(menuScene);
        stage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setTextFill(Color.WHITE);
        button.setStyle(
            "-fx-background-color: #228B22; " +  // Vert foncé
            "-fx-background-radius: 15px; " + // Bords arrondis
            "-fx-padding: 10px 20px; " +
            "-fx-border-color: white; " +
            "-fx-border-radius: 15px;"
        );

        // Effet au survol
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: #32CD32; " + // Vert clair
            "-fx-background-radius: 15px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-color: white; " +
            "-fx-border-radius: 15px;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: #228B22; " +
            "-fx-background-radius: 15px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-color: white; " +
            "-fx-border-radius: 15px;"
        ));

        return button;
    }

    private void startGame(boolean isMultiplayer) {
        GameController gameController = new GameController(stage, isMultiplayer, 15, 20, 30);
        gameController.lancerJeu();
    }
}
