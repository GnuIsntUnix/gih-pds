package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

        @FXML
        private TextField usernameField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private Button loginButton;

        @FXML
        public void handleLoginButtonAction(ActionEvent event) throws IOException {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();



            // TODO: Check the username and password against a database or other authentication system

            //if (/* username and password are valid */) {
                // TODO: Navigate to the main application screen
            //} else {
                // TODO: Display an error message to the user
            //}
    }
}