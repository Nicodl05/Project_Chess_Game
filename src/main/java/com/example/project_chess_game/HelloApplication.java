package com.example.project_chess_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Auxiliary.Interaction;
import Auxiliary.Spot;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    private int i = 0;
    private Pane mainPane;
    private ImageView[] list;
    private Image[] pieces;
    private Interaction thegame;
    private Spot start, end, start2, end2;
    private List<Spot> available;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ECHEC.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 512, 512);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
        HelloController controller = fxmlLoader.getController();
        controller.setApplication(this);
        mainPane = (Pane) scene.getRoot();
        list = mainPane.getChildren().toArray(new ImageView[0]);
        load_piece();
        initializeTerrain();
        thegame = new Interaction();

    }

    public void pick(String id) {
        int temp, elem1, elem2;
        if (i == 0) {
            try {
                start = thegame.player1.ChooseSpotStart_graph(thegame.board,
                        thegame.attacked_spot(thegame.player1.getColor()), id);
                available = start.getPiece().available_spot(thegame.board, start,
                        thegame.attacked_spot(thegame.player1.getColor()));
                for (Spot elem : available) {
                    temp = 8 * (elem.getX()) + (elem.getY() + 1);
                    list[temp].setImage(pieces[12]);
                    list[temp].setOpacity(0.5);
                }
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (i == 1) {
            try {
                end = thegame.player1.ChooseSpotEnd_graph(thegame.board, start,
                        thegame.attacked_spot(thegame.player1.getColor()), id);
                for (Spot elem : available) {
                    temp = 8 * (elem.getX()) + (elem.getY() + 1);
                    list[temp].setImage(null);
                }

                thegame.Move(start, end, thegame.player1);
                elem1 = 8 * (start.getX()) + (start.getY() + 1);
                elem2 = 8 * (end.getX()) + (end.getY() + 1);
                list[elem2].setImage(list[elem1].getImage());
                list[elem1].setImage(null);
                list[elem2].setOpacity(1);
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (i == 2) {
            try {
                start2 = thegame.player2.ChooseSpotStart_graph(thegame.board,
                        thegame.attacked_spot(thegame.player2.getColor()), id);
                available = start2.getPiece().available_spot(thegame.board, start2,
                        thegame.attacked_spot(thegame.player2.getColor()));
                for (Spot elem : available) {
                    temp = 8 * (elem.getX()) + (elem.getY() + 1);
                    list[temp].setImage(pieces[12]);
                    list[temp].setOpacity(0.5);
                }
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (i == 3) {
            try {
                end2 = thegame.player2.ChooseSpotEnd_graph(thegame.board, start2,
                        thegame.attacked_spot(thegame.player2.getColor()), id);
                for (Spot elem : available) {
                    temp = 8 * (elem.getX()) + (elem.getY() + 1);
                    list[temp].setImage(null);
                }

                thegame.Move(start2, end2, thegame.player2);
                elem1 = 8 * (start2.getX()) + (start2.getY() + 1);
                elem2 = 8 * (end2.getX()) + (end2.getY() + 1);
                list[elem2].setImage(list[elem1].getImage());
                list[elem1].setImage(null);
                list[elem2].setOpacity(1);
                i = 0;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeTerrain() {
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
    }

    private void load_piece() {
        String fichier = "file:src/main/resources/com/example/project_chess_game/";
        pieces = new Image[14];
        pieces[0] = new Image(fichier + "whitePawn.png");
        pieces[1] = new Image(fichier + "whiteBishop.png");
        pieces[2] = new Image(fichier + "whiteKnight.png");
        pieces[3] = new Image(fichier + "whiteRook.png");
        pieces[4] = new Image(fichier + "whiteQueen.png");
        pieces[5] = new Image(fichier + "whiteKing.png");
        pieces[6] = new Image(fichier + "blackPawn.png");
        pieces[7] = new Image(fichier + "blackBishop.png");
        pieces[8] = new Image(fichier + "blackKnight.png");
        pieces[9] = new Image(fichier + "blackRook.png");
        pieces[10] = new Image(fichier + "blackQueen.png");
        pieces[11] = new Image(fichier + "blackKing.png");
        pieces[12] = new Image(fichier + "green.png");
        pieces[13] = new Image(fichier + "red.png");
    }
}