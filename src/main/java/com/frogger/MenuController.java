package com.frogger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {
    private final Stage stage;

    public MenuController(Stage stage) {
        this.stage = stage;
    }

    public void showMenu() {
        VBox menuLayout = new VBox(10);
        Button soloButton = new Button("Solo");
        Button multiplayerButton = new Button("Multiplayer");

        soloButton.setOnAction(e -> startGame(false));
        multiplayerButton.setOnAction(e -> startGame(true));

        menuLayout.getChildren().addAll(soloButton, multiplayerButton);

        Scene menuScene = new Scene(menuLayout, 300, 200);
        stage.setScene(menuScene);
        stage.show();
    }

    private void startGame(boolean isMultiplayer) {
        GameController gameController = new GameController(stage, isMultiplayer, 15, 20, 30);
        gameController.lancerJeu();
    }
}