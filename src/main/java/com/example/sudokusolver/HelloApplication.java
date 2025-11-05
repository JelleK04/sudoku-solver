package com.example.sudokusolver;

import com.example.sudokusolver.components.SquareLabel;
import com.example.sudokusolver.components.SudokuGridPane;
import javafx.application.Application;
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
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    SudokuGridPane boardGrid = new SudokuGridPane(9);
    SquareLabel selectedSquare = null;
    GridPane rootGrid = new GridPane();
    Button clearBtn = new Button("Clear");
    Button solveBtn = new Button("Solve");

    @Override
    public void start(Stage stage) throws IOException {

        solveBtn.setPrefSize(100, 30);
        solveBtn.setOnAction(e -> {
            boardGrid.solveSudoku();
            boardGrid.renderSolvedBoard();
            showClearBtn(true);
        });

        ChoiceBox<String> sizeSelector = new ChoiceBox<>(FXCollections.observableArrayList("4 x 4", "9 x 9", "16 x 16"));

        clearBtn.setOnAction(e -> {
            int size = boardGrid.getBoardSize();
            rootGrid.getChildren().remove(boardGrid);
            boardGrid = new SudokuGridPane(size);
            rootGrid.add(boardGrid, 0, 0, 2, 3);
            showClearBtn(false);
            solveBtn.setDisable(false);
        });

        Label sizeLbl = new Label("Size:");
        sizeLbl.setFont(new Font(16));

        sizeSelector.getSelectionModel().select(1);
        sizeSelector.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            rootGrid.getChildren().remove(boardGrid);
            int size = 9;
            switch ((int) newValue) {
                case 0 -> size = 4;
                case 1 -> size = 9;
                case 2 -> size = 16;
            }
            boardGrid = new SudokuGridPane(size);
            rootGrid.add(boardGrid, 0, 0, 2, 3);
            System.out.println("BoardSize: " + size);
        });


        rootGrid.add(boardGrid, 0, 0, 2, 3);
        rootGrid.add(sizeLbl, 3, 0);
        rootGrid.add(sizeSelector, 4, 0);
        rootGrid.add(solveBtn, 4, 1);


        Scene scene = new Scene(rootGrid);

        scene.setOnKeyTyped(e -> boardGrid.setSquareValue(e));

        stage.setScene(scene);
        stage.setTitle("Sudoku Solver");
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();

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
