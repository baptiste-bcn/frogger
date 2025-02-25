package com.frogger.models;

import com.frogger.views.ObstacleManager;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
    private ObstacleManager.ObstacleType type;
    private double speed;

    public Obstacle(ObstacleManager.ObstacleType type, int y, int x, int width, int height, double speed) {
        super(width, height);
        this.type = type;
        this.speed = speed;

        setX(x);
        setY(y);

        if (type == ObstacleManager.ObstacleType.CAR) {
            setFill(Color.RED);
        } else {
            setFill(Color.BROWN);
        }

        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }

    public void move() {
        setX(getX() + speed);

        // Wrap around when off screen
        if (getX() > 800) {
            setX(-getWidth());
        } else if (getX() < -getWidth()) {
            setX(800);
        }
    }

    public ObstacleManager.ObstacleType getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean intersects(Frog frog) {
        return getBoundsInParent().intersects(frog.getBoundsInParent());
    }

}