package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    /**
     * ============================
     * FXML ELEMENTS SECTION
     * ============================
     **/

    @FXML
    private Button btnSolo;

    @FXML
    private Button btnDuo;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnQuit;

    private SceneController sceneController;

    @FXML
    public void initialize() {
        btnSolo.setOnAction(event -> sceneController.showGame(false));
        btnDuo.setOnAction(event -> sceneController.showGame(true));
        btnSettings.setOnAction(event -> sceneController.showSettings());
        btnQuit.setOnAction(event -> sceneController.getStage().close());
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}