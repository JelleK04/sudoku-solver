package com.example.sudokusolver;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    int boardSize = 9;
    GridPane boardGrid = new GridPane();
    Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2)));
    Border redBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2)));
    Square selectedSquare = new Square();
    Sudoku sudoku = new Sudoku(boardSize);
    GridPane rootGrid = new GridPane();
    Button clearBtn = new Button("Clear");
    Button solveBtn = new Button("Solve");

    @Override
    public void start(Stage stage) throws IOException {


        renderEmptyBoard(boardSize);

        solveBtn.setPrefSize(100, 30);
        solveBtn.setOnAction(e -> {
                    System.out.println("Solve button pressed");
                    sudoku.solve();
                    renderSolvedBoard();
                }
        );

        clearBtn.setOnAction(e -> {
            renderEmptyBoard(boardSize);
            sudoku.clear();
        });

        Label sizeLbl = new Label("Size:");
        sizeLbl.setFont(new Font(16));

        ChoiceBox sizeSelector = new ChoiceBox<>(FXCollections.observableArrayList("4 x 4", "9 x 9", "16 x 16"));
        sizeSelector.getSelectionModel().select(1);
        sizeSelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rootGrid.getChildren().remove(boardGrid);
                switch ((int) newValue) {
                    case 0:
                        boardSize = 4;
                        break;

                    case 1:
                        boardSize = 9;
                        break;

                    case 2:
                        boardSize = 16;
                        break;

                }
                sudoku = new Sudoku(boardSize);
                System.out.println("BoardSize: " + boardSize);
                renderEmptyBoard(boardSize);
                rootGrid.add(boardGrid, 0, 0, 3, 3);
            }
        });


        rootGrid.add(boardGrid, 0, 0, 3, 3);
        rootGrid.add(sizeLbl, 3, 0);
        rootGrid.add(sizeSelector, 4, 0);
        rootGrid.add(solveBtn, 4, 1);


        Scene scene = new Scene(rootGrid);

        scene.setOnKeyTyped(this::setSquareValue);

        stage.setScene(scene);
        stage.setTitle("Sudoku Solver");
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();
    }


    private void renderEmptyBoard(int boardSize) {
        boardGrid.getChildren().clear();
        showClearBtn(false);
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Square newCell = new Square(x, y);
                newCell.setPrefSize(50, 50);
                newCell.setFont(Font.font("Arial", 24));
                newCell.setAlignment(Pos.CENTER);
                newCell.setBorder(blackBorder);
                newCell.setOnMouseClicked(e -> {
                    switchSelectedSquare(newCell);
                });
                boardGrid.add(newCell, x, y);
            }
        }
    }

    private void switchSelectedSquare(Square square) {
        if (selectedSquare == square) {
            selectedSquare.setBorder(blackBorder);
            selectedSquare = new Square();
        } else {
            selectedSquare.setBorder(blackBorder);
            selectedSquare = square;
            selectedSquare.setBorder(redBorder);
        }

    }

    private void setSquareValue(KeyEvent e) {
        if (selectedSquare.getText().length() == 2) {
            selectedSquare.setText("");
        }

        char keyTyped = e.getCharacter().charAt(0);
        if (Character.isDigit(keyTyped)) {
            if (keyTyped == '0') {
                selectedSquare.setText("");
                System.out.println("input is 0");
                sudoku.setNumber(0, selectedSquare.getX(), selectedSquare.getY());
            } else {
                selectedSquare.setText(selectedSquare.getText() + keyTyped);
                sudoku.setNumber(Integer.parseInt(selectedSquare.getText()), selectedSquare.getX(), selectedSquare.getY());
            }
        }
        sudoku.print();
    }

    private void renderSolvedBoard() {
        boardGrid.getChildren().clear();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Label newCell = new Label(Integer.toString(sudoku.getValue(x, y)));
                newCell.setAlignment(Pos.CENTER);
                newCell.setFont(new Font("Arial", 24));
                newCell.setPrefSize(50, 50);
                newCell.setBorder(blackBorder);
                boardGrid.add(newCell, x, y);
            }
        }
        showClearBtn(true);
    }
    private void showClearBtn(boolean showBtn) {
        if (showBtn) {
            solveBtn.setDisable(true);
            rootGrid.add(clearBtn, 5, 1);
        } else {
            rootGrid.getChildren().remove(clearBtn);
            solveBtn.setDisable(false);
        }
    }
}
