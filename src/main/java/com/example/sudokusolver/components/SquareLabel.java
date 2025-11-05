package com.example.sudokusolver.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SquareLabel extends Label {
    private final int x;
    private final int y;

    private boolean isSelected;

    private final static Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2)));
    private final static Border redBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2)));

    public SquareLabel(int x, int y) {
        this.x = x;
        this.y = y;

        this.setSelected(false);

        setPrefSize(50, 50);
        setFont(Font.font("Arial", 24));
        setAlignment(Pos.CENTER);



//        if (x % 3 == 0 && y % 3 == 0)
//            setBorder(new Border(new BorderStroke(null, null, null, BorderStroke.THICK), new BorderStroke(null, null, null, BorderStroke.THIN), new BorderStroke(null, null, null, BorderStroke.THIN), new BorderStroke(null, null, null, BorderStroke.THIN)));
//        if (x % 3 == 0)
//            setBorder(new Border(new BorderStroke(null, null, null, BorderStroke.THICK), new BorderStroke(null, null, null, BorderStroke.THIN), new BorderStroke(null, null, null, BorderStroke.THIN), new BorderStroke(null, null, null, BorderStroke.THIN)));
//        else if (y % 3 == 0)
//            setBorder(new Border(new BorderStroke(null, null, null, BorderStroke.THIN), new BorderStroke(null, null, null, BorderStroke.THICK), new BorderStroke(null, null, null, BorderStroke.THIN), new BorderStroke(null, null, null, BorderStroke.THIN)));
//        else
//            setBorder(new Border(new BorderStroke(null, null, null, null)));
       }

    public SquareLabel() {
        this(-1, -1);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        this.setBorder(this.isSelected ? redBorder : blackBorder);
    }

    public boolean getSelected() {
        return this.isSelected;
    }
}

