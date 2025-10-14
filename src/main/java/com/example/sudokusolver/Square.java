package com.example.sudokusolver;

import javafx.scene.control.Label;

public class Square extends Label {
    private int x;
    private int y;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Square() {
        this(-1, -1);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

