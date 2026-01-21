# sudoku-solver

A program that is able to solve sudokus of the sizes 4x4, 9x9 and 16x16. The user can enter any (except for a few exceptions, see "known issues" paragraphs) starting state of the sudoku grid and the program will return a solved sudoku.
It uses a standard backtracking algorithm and was built using the Liberica Full 24 jdk.

## Instalation guide

In order to run the program you need to have the JavaFX module installed. There are two ways to do this.

### Install JavaFX through IDE
The easiest way to do get JavaFX is to download the Liberica Full 24 jdk through your IDE.
In IntelliJ go to "Project Settings" where you will find the "project" tab. Here you select the SDK dropdown menu and select "Download JDK". Then select "Bellsoft Liberica JDK (full)".

### Alternative ways to install JavaFX
It is also possible to download JavaFX via the Bellsoft website. See: https://bell-sw.com/pages/downloads/#jdk-25-lts. Alternatively you can download JavaFX seperately from the GluonHQ website. See: https://openjfx.io/openjfx-docs/

## Known bugs

The program is not always able to solve 16x16 sudoku's. In such a case the "clear" button can be pressed to reset the program.
