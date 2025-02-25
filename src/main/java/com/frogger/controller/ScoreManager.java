package com.frogger.controller;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreManager {
    private int score = 0;
    private int lives = 3;
    private int level = 1;
    private Label scoreLabel;
    private Label livesLabel;
    private Label levelLabel;

    public ScoreManager() {
        scoreLabel = createLabel("Score: 0", 10, 10);
        livesLabel = createLabel("Lives: 3", 10, 40);
        levelLabel = createLabel("Level: 1", 10, 70);
    }

    private Label createLabel(String text, int x, int y) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Arial", 20));
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    public void addToPane(Pane pane) {
        pane.getChildren().addAll(scoreLabel, livesLabel, levelLabel);
    }

    public void addPoints(int points) {
        score += points;
        updateScore();
    }

    public void loseLife() {
        lives--;
        updateScore();
    }

    public void levelUp() {
        level++;
        updateScore();
    }

    public void updateScore() {
        scoreLabel.setText("Score: " + score);
        livesLabel.setText("Lives: " + lives);
        levelLabel.setText("Level: " + level);
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }
}