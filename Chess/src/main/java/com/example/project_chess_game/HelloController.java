package com.example.project_chess_game;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    private HelloApplication helloApplication;

    public void setApplication(HelloApplication hello) {
        this.helloApplication = hello;
    }

    @FXML
    protected void onClick(MouseEvent event) {
        ImageView temp = (ImageView) event.getSource();
        this.helloApplication.ia(temp.getId().toString());
    }
}