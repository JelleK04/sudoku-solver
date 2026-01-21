package com.example.sudokusolver.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SquareLabel extends Label {
    private final int x;
    private final int y;
    private int boardSize;
    private boolean isSelected;

    public SquareLabel(int x, int y, int boardSize) {
        this.x = x;
        this.y = y;
        this.boardSize = boardSize;
        this.setSelected(false);

        setPrefSize(50, 50);
        setFont(Font.font("Arial", 24));
        setAlignment(Pos.CENTER);
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        this.setBorder(createCellBorder(y, x, isSelected, boardSize));
    }

    public boolean getSelected() {
        return this.isSelected;
    }

    public static Border createCellBorder(int row, int col, boolean isSelected, int boardSize) {
        BorderStrokeStyle style = BorderStrokeStyle.SOLID;
        Color color = (isSelected) ? Color.RED : Color.BLACK;

        double thin = 1;
        double thick = 3;
        int regionSize = (int) Math.sqrt(boardSize);
        return new Border(new BorderStroke(
                color, color, color, color,
                style, style, style, style,
                CornerRadii.EMPTY,
                new BorderWidths(
                        (isSelected) ? thick : (row % regionSize == 0) ? thick : thin, //top
                        (isSelected) ? thick : (col % regionSize == (regionSize - 1)) ? thick : thin, //right
                        (isSelected) ? thick :(row == (boardSize - 1)) ? thick : ((row % regionSize == (regionSize - 1)) ? thick : thin), //bottom
                        (isSelected) ? thick : (col % regionSize == 0) ? thick : thin //left
                ),
                null
        ));
    }
}

