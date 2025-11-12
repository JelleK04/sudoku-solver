package com.example.sudokusolver;

import com.example.sudokusolver.components.SudokuGridPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    SudokuGridPane boardGrid = new SudokuGridPane(9);
    HBox root = new HBox();
    VBox rightVBox = new VBox();
    Button clearBtn = new Button("Clear");
    Button solveBtn = new Button("Solve");

    @Override
    public void start(Stage stage) throws IOException {

        solveBtn.setPrefSize(100, 30);
        solveBtn.setOnAction(_ -> {
            boardGrid.solveSudoku();
            boardGrid.renderSolvedBoard();
            showClearBtn(true);
        });

        ChoiceBox<String> sizeSelector = new ChoiceBox<>(FXCollections.observableArrayList("4 x 4", "9 x 9", "16 x 16"));

        clearBtn.setOnAction(_ -> {
            boardGrid.clear();
            showClearBtn(false);
            solveBtn.setDisable(false);
        });

        Label sizeLbl = new Label("Size:");
        sizeLbl.setFont(new Font(16));

        sizeSelector.getSelectionModel().select(1);
        sizeSelector.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            root.getChildren().remove(boardGrid);
            int size = 9;
            switch ((int) newValue) {
                case 0 -> size = 4;
                case 2 -> size = 16;
            }
            boardGrid = new SudokuGridPane(size);
            root.getChildren().add(boardGrid);
            System.out.println("BoardSize: " + size);
            showClearBtn(false);
        });

        rightVBox.getChildren().addAll(sizeLbl, sizeSelector, solveBtn);
        rightVBox.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(boardGrid);
        root.getChildren().add(rightVBox);


        Scene scene = new Scene(root);

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
            rightVBox.getChildren().add(clearBtn);
        } else {
            rightVBox.getChildren().remove(clearBtn);
            solveBtn.setDisable(false);
        }
    }
}
