package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {
    @FXML
    private Button btnBack;

    private SceneController sceneController;

    @FXML
    public void initialize() {
        btnBack.setOnAction(event -> sceneController.showMenu());
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
