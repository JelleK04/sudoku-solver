package com.example.sudokusolver;

import java.util.Arrays;

public class Sudoku {
    private int boardSize;
    private int[] numbers;
    private int regionSize;

    Sudoku(int boardSize) {
        this.boardSize = boardSize;
        this.numbers = new int[boardSize * boardSize];
        this.regionSize = (int) Math.sqrt(boardSize);
    }

    public void setNumber(int x, int y, int input) {
        numbers[x + y * this.boardSize] = input;
    }

    public void print() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                System.out.print(numbers[x + y * this.boardSize] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getValue(int x, int y) {
        return this.numbers[x + y * boardSize];
    }

    //Check if input is safe
    private boolean isSafe(int x, int y, int input) {
        //check if number exists in row
        for (int i = 0; i < boardSize; i++) {
            if (getValue(i, y) == input) {
                return false;
            }
        }
        //check if number exists in column
        for (int i = 0; i < boardSize; i++) {
            if (getValue(x, i) == input) {
                return false;
            }
        }
        //check if number exists in region
        return regionIsSafe(x, y, input);

    }

    private boolean regionIsSafe(int x, int y, int input) {
        int startRow = y - (y % regionSize);
        int startColumn = x - (x % regionSize);

        for (int i = 0; i < regionSize; i++) {
            for (int j = 0; j < regionSize; j++) {
                if (getValue(startColumn + j, startRow + i) == input) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solve() {
        this.solve(0, 0);
    }

    //function that recursively solves sudoku
    public boolean solve(int x, int y) {
        if (x == boardSize && y == boardSize - 1) {
            return true;
        }
        if (x == boardSize) {
            x = 0;
            y++;
        }

        if (this.getValue(x,y) != 0) {
            return this.solve(x + 1,y);
        }
        for (int i = 1; i <= boardSize; i++) {
            if (this.isSafe(x, y, i)) {
                this.setNumber(x, y, i);
                if (this.solve(x + 1, y)) {
                    return true;
                }
                this.setNumber(x, y, 0);
            }
        }
        return false;
    }

    public void clear() {
        Arrays.fill(this.numbers, 0);
    }
}



