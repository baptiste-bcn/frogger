package com.frogger.model;

public class Grid {
    private final int rows;
    private final int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
