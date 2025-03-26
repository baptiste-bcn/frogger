package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SettingsController {

    @FXML
    private CheckBox checkFullScreen;

    @FXML
    private Button btnBack;

    @FXML
    public void initialize() {
        btnBack.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                Stage stage = (Stage) newScene.getWindow();
                checkFullScreen.setSelected(stage.isFullScreen());

                checkFullScreen.setOnAction(event -> {
                    stage.setFullScreen(checkFullScreen.isSelected());
                });

                btnBack.setOnAction(event -> goBackToMenu());
            }
        });
    }

    private void goBackToMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frogger/view/MenuLayout.fxml"));
            VBox menuLayout = loader.load();
            Scene menuScene = new Scene(menuLayout);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}