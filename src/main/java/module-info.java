module com.example.sudokusolver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    opens com.example.sudokusolver to javafx.fxml;
    exports com.example.sudokusolver;
    exports com.example.sudokusolver.components;
    opens com.example.sudokusolver.components to javafx.fxml;
}