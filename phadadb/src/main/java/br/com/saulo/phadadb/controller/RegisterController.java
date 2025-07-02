package br.com.saulo.phadadb.controller;

import java.io.IOException;

import br.com.saulo.phadadb.App;
import br.com.saulo.phadadb.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegisterController {

    private final UserService userService;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private CheckBox termsCheckbox;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void processRegistration() {
        String name = fullNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        boolean isTermsCheckboxSelected = termsCheckbox.isSelected();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("All fields are required");
            return;
        }

        if (!isTermsCheckboxSelected) {
            System.out.println("You must accept the terms and conditions");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("The passwords need to be equal");
            return;
        }
        try {
            userService.registerUser(name, email, password);
            System.out.println("User register success!");
            App.setRoot("Login");
        } catch (Exception e) {
            System.out.println("User register failed: " + e.getMessage());
        }
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
