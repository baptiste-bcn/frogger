package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private CheckBox checkFullScreen;

    @FXML
    private Button btnBack;

    private SceneController sceneController;

    @FXML
    public void initialize() {

        checkFullScreen.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                Stage stage = (Stage) checkFullScreen.getScene().getWindow();

                checkFullScreen.setSelected(stage.isFullScreen());
                checkFullScreen.setOnAction(event -> {
                    stage.setFullScreen(checkFullScreen.isSelected());
                });
            }
        });

        btnBack.setOnAction(event -> {
            if (sceneController != null) {
                sceneController.showMenu();
            }
        });

    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}