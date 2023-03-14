package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button buttonLit;

    @FXML
    private BorderPane centerBorder;

    public void onLitClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("affichageLits.fxml"));
        centerBorder.setCenter(fxmlLoader);
    }
}
