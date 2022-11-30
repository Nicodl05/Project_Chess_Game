package com.example.project_chess_game;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String fichier = "file:src/main/resources/com/example/project_chess_game/";
        Image[] pieces = {
                new Image(fichier + "whitePawn.png"),
                new Image(fichier + "whiteBishop.png"),
                new Image(fichier + "whiteKnight.png"),
                new Image(fichier + "whiteRook.png"),
                new Image(fichier + "whiteQueen.png"),
                new Image(fichier + "whiteKing.png"),
                new Image(fichier + "blackPawn.png"),
                new Image(fichier + "blackBishop.png"),
                new Image(fichier + "blackKnight.png"),
                new Image(fichier + "blackRook.png"),
                new Image(fichier + "blackQueen.png"),
                new Image(fichier + "blackKing.png"),
        };
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ECHEC.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 512, 512);
        stage.setTitle("Chess");
        stage.setScene(scene);
        Pane mainPane = (Pane) scene.getRoot();
        ImageView[] list = mainPane.getChildren().toArray(new ImageView[0]);
        list[57].setImage(pieces[3]);
        list[58].setImage(pieces[2]);
        list[59].setImage(pieces[1]);
        list[60].setImage(pieces[4]);
        list[61].setImage(pieces[5]);
        list[62].setImage(pieces[1]);
        list[63].setImage(pieces[2]);
        list[64].setImage(pieces[3]);
        for (int i = 49; i < 57; i++) {
            list[i].setImage(pieces[0]);
        }

        list[1].setImage(pieces[9]);
        list[2].setImage(pieces[8]);
        list[3].setImage(pieces[7]);
        list[4].setImage(pieces[10]);
        list[5].setImage(pieces[11]);
        list[6].setImage(pieces[7]);
        list[7].setImage(pieces[8]);
        list[8].setImage(pieces[9]);
        for (int i = 9; i < 17; i++) {
            list[i].setImage(pieces[6]);
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}