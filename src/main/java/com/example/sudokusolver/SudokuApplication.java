package com.example.sudokusolver;

import com.example.sudokusolver.components.SudokuGridPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class SudokuApplication extends Application {
    SudokuGridPane boardGrid = new SudokuGridPane(9);
    HBox root = new HBox();
    VBox rightVBox = new VBox();
    Button clearBtn = new Button("Clear");
    Button solveBtn = new Button("Solve");

    @Override
    public void start(Stage stage) {

        solveBtn.setPrefSize(100, 30);
        solveBtn.setOnAction(_ -> {
            solveBtn.setDisable(true);

            Task<Void> solveTask = new Task() {
                @Override
                protected Void call() {
                    boardGrid.solveSudoku();
                    return null;
                }
            };

            solveTask.setOnSucceeded(e -> {
                boardGrid.renderSolvedBoard();
                clearBtn.setDisable(false);
            });
        });

        ChoiceBox<String> sizeSelector = new ChoiceBox<>(FXCollections.observableArrayList("4 x 4", "9 x 9", "16 x 16"));

        clearBtn.setOnAction(_ -> {
            boardGrid.clear();
            solveBtn.setDisable(false);
            clearBtn.setDisable(true);
        });

        Label sizeLbl = new Label("Size:");
        sizeLbl.setFont(new Font(16));

        sizeSelector.getSelectionModel().select(1);
        sizeSelector.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int size = 9;
            switch ((int) newValue) {
                case 0 -> size = 4;
                case 2 -> size = 16;
            }
            boardGrid.changeSize(size);
            solveBtn.setDisable(false);
            System.out.println("BoardSize: " + size);
            if (size == 16) {
                System.out.println("Maximize!");
                stage.setMaximized(true);
            } else {
                stage.setMinHeight(size * 52 + 50);
                stage.setMinWidth(size * 55 + 120);
            }
        });

        rightVBox.getChildren().addAll(sizeLbl, sizeSelector, solveBtn);
        rightVBox.getChildren().add(clearBtn);
        rightVBox.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(boardGrid);
        root.getChildren().add(rightVBox);
        root.setSpacing(10);
        HBox.setMargin(boardGrid, new Insets(5, 0, 0, 5));
        VBox.setMargin(sizeSelector, new Insets(0, 0,10, 0));
        VBox.setMargin(solveBtn, new Insets(0, 0,5, 0));
        clearBtn.setDisable(true);

        Scene scene = new Scene(root);

        scene.setOnKeyTyped(e -> {boardGrid.setSquareValue(e); if (!boardGrid.isEmpty()){clearBtn.setDisable(false);}});
        stage.setScene(scene);
        stage.setTitle("Sudoku Solver");
        stage.setMinWidth(500);
        stage.setMinHeight(300);
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();
    }
}