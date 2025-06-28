package br.com.saulo.phadadb.controller;

import java.io.IOException;

import br.com.saulo.phadadb.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class RegisterController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField termsCheckbox;


    @FXML
    private void processRegistration() {
        System.out.println("Registration processed");
    }

    @FXML
    private void handleSignIn() {
        try {
            App.setRoot("Login");
        } catch (IOException e) {
            System.err.println("Error loading Login view: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
