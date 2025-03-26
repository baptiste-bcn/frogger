package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameController {
    @FXML
    private Text text;

    @FXML
    public void changeText() {
        text.setText("Hello modifié!");
        text.getStyleClass().add("modified-text");
    }
}
