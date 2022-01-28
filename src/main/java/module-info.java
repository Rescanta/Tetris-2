module game.tetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.tetris to javafx.fxml;
    exports game.tetris;
}