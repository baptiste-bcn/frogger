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
        checkFullScreen.setSelected(true);
        btnBack.setOnAction(event -> sceneController.showMenu());
        checkFullScreen.setOnAction(event -> {
            Stage stage = sceneController.getStage();
            stage.setFullScreen(checkFullScreen.isSelected());
        });
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}