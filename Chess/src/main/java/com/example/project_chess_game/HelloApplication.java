package com.example.project_chess_game;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Auxiliary.Interaction;
import Auxiliary.Spot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    private int i = 0;
    private Pane mainPane;
    private Pane[] panes;
    private ImageView[] list;
    private Label error;
    private Image[] pieces;
    private Interaction thegame;
    private Spot start, end, start2, end2;
    private List<Spot> available;
    private PauseTransition hitAnimation;
    private FadeTransition fade;
    private String gameWinner;
    private boolean endgame = false;

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
        panes = mainPane.getChildren().toArray(new Pane[0]);
        list = panes[1].getChildren().toArray(new ImageView[0]);
        error = (Label) panes[0].getChildren().get(1);
        error.setAlignment(Pos.CENTER);
        error.setOpacity(0);
        hitAnimation = new PauseTransition(Duration.seconds(1));
        fade = new FadeTransition();
        fade.setDuration(Duration.millis(2000));
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setCycleCount(1);
        fade.setNode(error);
        hitAnimation.setOnFinished(e -> {
            fade.play();

        });
        load_piece();
        initializeTerrain();
        thegame = new Interaction();

    }

    public void writeFile() {
        String filePath = "nim.txt";

        try {
            // Open the file in write mode
            FileWriter writer = new FileWriter(filePath);

            // Write the new content to the file
            writer.write("Ordinateur,Adrien\nNoir,Blanc\n");

            // Close the file
            writer.close();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String retrievewinner() {
        String filePath = "nim.txt";
        String lastWord = "";

        try {
            // Open the file in read mode
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Read each line and split it into words
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineWords = line.split(",");
                if (lineWords.length > 0) {
                    lastWord = lineWords[lineWords.length - 1];
                }
            }

            // Print the last word
            System.out.println("Last word: " + lastWord);

            // Close the file
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return lastWord;
    }

    public void startNimGame() {
        try {
            // Create a process builder for the executable file
            ProcessBuilder pb = new ProcessBuilder("Nim.exe");

            // Start the process
            Process process = pb.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Print the exit code of the process
            System.out.println("Exit code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void miniGames() {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
       /* switch (randomNumber){
            case 1:
                startNimGame();
                break;
            case 2:
                break;
            case 3:
                break;
            case default:
                break;
        }*/
        startNimGame();
    }

    public void pick(String id) {
        int temp, elem1, elem2;
        if (i == 0) {
            try {
                start = thegame.player1.ChooseSpotStart_graph(thegame.board,
                        thegame.attacked_spot(thegame.player1.getColor()), id);
                available = start.getPiece().available_spot(thegame.board, start, thegame.attacked_spot(thegame.player1.getColor()));

                for (Spot elem : available) {
                    temp = 8 * (elem.getX()) + (elem.getY() + 1);
                    list[temp].setImage(pieces[12]);
                    list[temp].setOpacity(0.5);
                }
                i++;
            } catch (Exception e) {
                error.setText(e.getMessage());
                error.setOpacity(1);
                hitAnimation.playFromStart();
            }
        } else if (i == 1) {
            try {
                end = thegame.player1.ChooseSpotEnd_graph(thegame.board, start,
                        thegame.attacked_spot(thegame.player1.getColor()), id);
                for (Spot elem : available) {
                    temp = 8 * (elem.getX()) + (elem.getY() + 1);
                    list[temp].setImage(null);
                }
                if (end.getPiece() != null) {
                    if (end.getPiece().getType() != null) {
                        if (end.getPiece().getType() == start.getPiece().getType()) {
                            writeFile();
                            System.out.println("Piece j1 : " + start.getPiece().getType());
                            System.out.println("Piece j2 : " + end.getPiece().getType());
                            if (end.getPiece().getValue() < 3) {
                                startNimGame();
                            } else {
                                miniGames();
                            }
                            String winner = retrievewinner();
                            if (winner.equals("Blanc")) {
                                thegame.Move(start, end, thegame.player1);
                                elem1 = 8 * (start.getX()) + (start.getY() + 1);
                                elem2 = 8 * (end.getX()) + (end.getY() + 1);
                                list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                                list[elem1 + 64].setImage(null);
                                list[elem2 + 64].setOpacity(1);
                            } else {
                                thegame.Move(end, start, thegame.player1);
                                elem1 = 8 * (end.getX()) + (end.getY() + 1);
                                elem2 = 8 * (start.getX()) + (start.getY() + 1);
                                list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                                list[elem1 + 64].setImage(null);
                                list[elem2 + 64].setOpacity(1);

                            }
                        } else {
                            thegame.Move(start, end, thegame.player1);
                            elem1 = 8 * (start.getX()) + (start.getY() + 1);
                            elem2 = 8 * (end.getX()) + (end.getY() + 1);
                            list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                            list[elem1 + 64].setImage(null);
                            list[elem2 + 64].setOpacity(1);
                        }

                    }
                }
                if (end.getPiece() == null) {
                    thegame.Move(start, end, thegame.player1);
                    elem1 = 8 * (start.getX()) + (start.getY() + 1);
                    elem2 = 8 * (end.getX()) + (end.getY() + 1);
                    list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                    list[elem1 + 64].setImage(null);
                    list[elem2 + 64].setOpacity(1);

                }
                i++;
            } catch (Exception e) {
                error.setText(e.getMessage());
                error.setOpacity(1);
                hitAnimation.playFromStart();
            }
            try {
                if (thegame.Checkmate(thegame.player2.getColor())) {
                    gameWinner = "player 1";
                    endgame = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (i == 2) {
            try {
                start2 = thegame.player2.ChooseSpotStart_graphOrdi(thegame.board,
                        thegame.attacked_spot(thegame.player2.getColor()));

                available = start2.getPiece().available_spot(thegame.board, start2,
                        thegame.attacked_spot(thegame.player2.getColor()));
                i++;
            } catch (Exception e) {
                error.setText(e.getMessage());
                error.setOpacity(1);
                hitAnimation.playFromStart();
            }

        } else if (i == 3) {
            try {
                end2 = thegame.player2.ChooseSpotEnd_graphOrdi(thegame.board, start2,
                        thegame.attacked_spot(thegame.player2.getColor()));
                if (end2.getPiece() != null) {
                    if (end2.getPiece().getType() != null) {
                        if (start2.getPiece().getType() == end2.getPiece().getType()) {
                            writeFile();
                            System.out.println("Piece j1 : " + start2.getPiece().getType());
                            System.out.println("Piece j2 : " + end2.getPiece().getType());
                            if (end.getPiece().getValue() < 3) {
                                startNimGame();
                            } else {
                                miniGames();
                            }
                            String winner = retrievewinner();
                            if (winner.equals("Noir")) {
                                thegame.Move(start2, end2, thegame.player2);
                                elem1 = 8 * (start2.getX()) + (start2.getY() + 1);
                                elem2 = 8 * (end2.getX()) + (end2.getY() + 1);
                                list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                                list[elem1 + 64].setImage(null);
                                list[elem2 + 64].setOpacity(1);
                            } else {
                                thegame.Move(start2, end2, thegame.player2);
                                elem1 = 8 * (end2.getX()) + (end2.getY() + 1);
                                elem2 = 8 * (start2.getX()) + (start2.getY() + 1);
                                list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                                list[elem1 + 64].setImage(null);
                                list[elem2 + 64].setOpacity(1);
                            }
                        } else {
                            thegame.Move(start2, end2, thegame.player2);
                            elem1 = 8 * (start2.getX()) + (start2.getY() + 1);
                            elem2 = 8 * (end2.getX()) + (end2.getY() + 1);
                            list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                            list[elem1 + 64].setImage(null);
                            list[elem2 + 64].setOpacity(1);
                        }

                    }
                } else {
                    thegame.Move(start2, end2, thegame.player2);
                    elem1 = 8 * (start2.getX()) + (start2.getY() + 1);
                    elem2 = 8 * (end2.getX()) + (end2.getY() + 1);
                    list[elem2 + 64].setImage(list[elem1 + 64].getImage());
                    list[elem1 + 64].setImage(null);
                    list[elem2 + 64].setOpacity(1);
                }
                i = 0;
                try {
                    if (thegame.Checkmate(thegame.player2.getColor())) {
                        gameWinner = "player 2";
                        endgame = true;
                        i = 5;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                error.setText(e.getMessage());
                error.setOpacity(1);
                hitAnimation.playFromStart();
            }
        }

        if (i == 5) {
            if (gameWinner.equals("player 1")) {
                System.out.println("Player 1 wins");
            } else {
                System.out.println("Player 2 wins");
            }
        }
    }


    public static void main(String[] args) {
        launch();
    }

    private void initializeTerrain() {
        list[57 + 64].setImage(pieces[3]);
        list[58 + 64].setImage(pieces[2]);
        list[59 + 64].setImage(pieces[1]);
        list[60 + 64].setImage(pieces[4]);
        list[61 + 64].setImage(pieces[5]);
        list[62 + 64].setImage(pieces[1]);
        list[63 + 64].setImage(pieces[2]);
        list[64 + 64].setImage(pieces[3]);
        for (int i = 49; i < 57; i++) {
            list[i + 64].setImage(pieces[0]);
        }

        list[1 + 64].setImage(pieces[9]);
        list[2 + 64].setImage(pieces[8]);
        list[3 + 64].setImage(pieces[7]);
        list[4 + 64].setImage(pieces[10]);
        list[5 + 64].setImage(pieces[11]);
        list[6 + 64].setImage(pieces[7]);
        list[7 + 64].setImage(pieces[8]);
        list[8 + 64].setImage(pieces[9]);
        for (int i = 9; i < 17; i++) {
            list[i + 64].setImage(pieces[6]);
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