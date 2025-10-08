package com.example.sudokusolver;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    int boardSize = 9;
    GridPane boardGrid;
    Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2)));
    Border redBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2)));
    Label selectedSquare = new Label();

    @Override
    public void start(Stage stage) throws IOException {
        GridPane rootGrid = new GridPane();

        boardGrid = renderEmptyBoard(9);

        Button solveBtn = new Button("Solve");
        solveBtn.setPrefSize(100, 30);

        Label sizeLbl = new Label("Size:");

        ChoiceBox sizeSelector = new ChoiceBox<>(FXCollections.observableArrayList("4 x 4", "9 x 9", "16 x 16"));
        sizeSelector.getSelectionModel().select(1);
        sizeSelector.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rootGrid.getChildren().remove(boardGrid);
                switch ((int) newValue) {
                    case 0:
                        boardSize = 4;
                        System.out.println(boardSize);
                        boardGrid = renderEmptyBoard(boardSize);
                        break;

                    case 1:
                        boardSize = 9;
                        System.out.println(boardSize);
                        boardGrid = renderEmptyBoard(boardSize);
                        break;

                    case 2:
                        boardSize = 16;
                        System.out.println(boardSize);
                        boardGrid = renderEmptyBoard(boardSize);
                        break;

                }
                rootGrid.add(boardGrid, 0, 0, 3, 3);
            }
        });


        rootGrid.add(boardGrid, 0, 0, 3, 3);
        rootGrid.add(sizeLbl, 3, 0);
        rootGrid.add(sizeSelector, 4, 0);
        rootGrid.add(solveBtn, 4, 1);


        Scene scene = new Scene(rootGrid);

        scene.setOnKeyTyped(e -> {
            setSquare(e);
        });

        stage.setScene(scene);
        stage.setTitle("Sudoku Solver");
        stage.setWidth(1500);
        stage.setHeight(800);
        stage.show();
    }


    private GridPane renderEmptyBoard(int boardSize) {
        GridPane emptyBoard = new GridPane();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Label newCell = new Label();
                newCell.setPrefSize(50, 50);
                newCell.setFont(Font.font("Arial", 24));
                newCell.setAlignment(Pos.CENTER);
                newCell.setBorder(blackBorder);
                newCell.setOnMouseClicked(e -> {
                    newCell.setBorder(redBorder);
                    resetSquareBorder();
                    selectedSquare = newCell;
                });
                emptyBoard.add(newCell, x, y);
            }
        }
        return emptyBoard;
    }

    private void resetSquareBorder() {
        selectedSquare.setBorder(blackBorder);

    }

    private void setSquare(KeyEvent e) {
        char[] inputArray = e.getCharacter().toCharArray();
        char input = inputArray[0];
        if (Character.isDigit(input)) {
            if (input == '0') {
                selectedSquare.setText("");
                System.out.println("input is 0");
            } else {
                selectedSquare.setText(e.getCharacter());
            }
        }
    }
}
