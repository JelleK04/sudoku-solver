# sudoku-solver
![image of the programs user interface](https://github.com/JelleK04/sudoku-solver/blob/main/SudokuSolverGUI.png)

A program build using Java and JavaFx that is able to solve sudokus. The user can enter almost any (for exceptions, see [known bugs](#known-bugs)) starting state of the sudoku grid and the program will return a solved sudoku.
It uses a backtracking algorithm and was built using the Liberica Full 24 jdk.

* [Installation](#installation-guide)
* How to use
* Bugs

## Installation guide

In order to run the program you need to have the JavaFX module installed. There are two ways to do this. The easiest way to do get JavaFX is to download the Liberica Full 24 jdk through your IDE.

### Install JavaFX through IntelliJ IDE
First, go to the Project Structure menu.

![Project Structure](https://github.com/JelleK04/sudoku-solver/blob/main/images/ProjectStructureButton.png) 

There you will find the "project" tab. Here you select the SDK dropdown menu and select "Download JDK".

![Button to press to download JDKs](https://github.com/JelleK04/sudoku-solver/blob/main/images/DownloadJDKButton.png)

Then select "Bellsoft Liberica JDK (full)" and press download.

![The JDK that should be installed](https://github.com/JelleK04/sudoku-solver/blob/main/images/JDKToDownload.png)


### Alternative ways to install JavaFX
It is also possible to [download JavaFX via the Bellsoft website](https://bell-sw.com/pages/downloads/#jdk-25-lts).  
Alternatively you can [download JavaFX seperately from the GluonHQ website](https://openjfx.io/openjfx-docs/).

## Known bugs

The program is not always able to solve 16x16 sudoku's. In such a case the "clear" button can be pressed to reset the program.
