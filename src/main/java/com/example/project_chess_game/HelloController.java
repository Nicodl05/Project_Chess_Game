package com.example.project_chess_game;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    protected void onClick(MouseEvent event) {
        ImageView temp = (ImageView) event.getSource();
        System.out.println(temp.getId());
    }
}