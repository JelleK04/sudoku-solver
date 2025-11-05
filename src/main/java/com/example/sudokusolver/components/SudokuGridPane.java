package com.example.sudokusolver.components;

import com.example.sudokusolver.Sudoku;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SudokuGridPane extends Pane {
    private final GridPane grid;
    private Sudoku sudoku;
    private Border selectedBorder;
    private int selectedIndex;

    public SudokuGridPane(int boardSize) {
        this.sudoku = new Sudoku(boardSize);
        this.grid = new GridPane();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                final int index = j + i * boardSize;
                SquareLabel square = new SquareLabel(j, i);
                square.setPrefSize(50, 50);
                square.setFont(new Font("Arial", 24));
                square.setOnMouseClicked(_ -> {
                    if (((SquareLabel)this.grid.getChildren().get(selectedIndex)).getSelected()) {
                        ((SquareLabel) this.grid.getChildren().get(selectedIndex)).setSelected(false);
                    } else {
                        ((SquareLabel) this.grid.getChildren().get(selectedIndex)).setSelected(false);
                        selectedIndex = index;
                        ((SquareLabel) this.grid.getChildren().get(selectedIndex)).setSelected(true);
                    }
                });
                this.grid.add(square, j, i);
            }
        }

        getChildren().add(this.grid);
    }
    public int getBoardSize() {
        return this.sudoku.getBoardSize();
    }
    public void reset () {
        sudoku = new Sudoku(this.getBoardSize());
    }

    public void renderSolvedBoard() {
        this.grid.getChildren().clear();
        for (int y = 0; y < this.sudoku.getBoardSize(); y++) {
            for (int x = 0; x < this.sudoku.getBoardSize(); x++) {
                Label newLabel = new Label(Integer.toString(this.sudoku.getValue(x, y)));
                newLabel.setAlignment(Pos.CENTER);
                newLabel.setFont(new Font("Arial", 24));
                newLabel.setPrefSize(50, 50);
                newLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
                this.grid.add(newLabel, x, y);
            }
        }
    }

    public void setSquareValue(KeyEvent e) {
        SquareLabel selectedSquare = (SquareLabel) this.grid.getChildren().get(selectedIndex);
        if (!selectedSquare.getSelected()){
            return;
        }

        if ((this.sudoku.getBoardSize() >= 16 && selectedSquare.getText().length() == 2) || (this.sudoku.getBoardSize() < 16 && selectedSquare.getText().length() == 1)) {
            selectedSquare.setText("");
        }

        char keyTyped = e.getCharacter().charAt(0);
        if (Character.isDigit(keyTyped)) {
            if (keyTyped == '0') {
                selectedSquare.setText("");
                this.sudoku.setNumber(selectedSquare.getX(), selectedSquare.getY(), 0);
            } else {
                selectedSquare.setText(selectedSquare.getText() + keyTyped);
                this.sudoku.setNumber(selectedSquare.getX(), selectedSquare.getY(), Integer.parseInt(selectedSquare.getText()));
            }
        }
        this.sudoku.print();
    }

    public void solveSudoku() {
        this.sudoku.solve();
    }
}