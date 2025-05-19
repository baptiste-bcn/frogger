package com.frogger.model;

public enum Difficulty {
    EASY("Easy", 1.5), NORMAL("Normal", 1.3), HARD("Hard", 1.2);

    private final String label;
    private final double speedMultiplier;

    Difficulty(String label, double speedMultiplier) {
        this.label = label;
        this.speedMultiplier = speedMultiplier;
    }

    public String getLabel() {
        return label;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    @Override
    public String toString() {
        return label;
    }
}