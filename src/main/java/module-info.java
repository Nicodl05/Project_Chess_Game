module com.example.project_chess_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.example.project_chess_game to javafx.fxml;
    exports com.example.project_chess_game;
}