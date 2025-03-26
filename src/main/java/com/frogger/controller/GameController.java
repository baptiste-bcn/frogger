package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import com.frogger.model.Grid;
import javafx.application.Platform;

public class GameController {
    @FXML
    private GridPane grid;

    @FXML
    private Button btnBack;

    private SceneController sceneController;

    private final Grid gridModel = new Grid(10, 10);

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            btnBack.setOnAction(event -> sceneController.showMenu());

            for (int row = 0; row < gridModel.getRows(); row++) {
                for (int col = 0; col < gridModel.getCols(); col++) {
                    Rectangle cell = new Rectangle(50, 50, Color.LIGHTGRAY);
                    cell.setStroke(Color.BLACK);
                    grid.add(cell, col, row);
                }
            }
        });
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
