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

    public void setNumber(int value, int x, int y) {
        numbers[x + y * this.boardSize] = value;
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

    public void isSafe() {

    }

    public void solve() {
        solve(0, 0);
    }

    //function that recursively solves sudoku
    public void solve(int x, int y) {

    }

    public void clear() {
        Arrays.fill(this.numbers, 0);
    }
}



