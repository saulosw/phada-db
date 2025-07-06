package br.com.saulo.phadadb.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.saulo.phadadb.App;
import br.com.saulo.phadadb.model.User;
import br.com.saulo.phadadb.service.UserService;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoginController implements Initializable {

    @FXML
    private ImageView loginLeftImage;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberCheckbox;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signUpLink;

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addEntryAnimations();
        addHoverEffects();
    }

    private void addEntryAnimations() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);

        fadeTransition.setNode(emailField.getParent().getParent());
        fadeTransition.play();
    }

    private void addHoverEffects() {
        loginButton.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), loginButton);
            scaleTransition.setToX(1.02);
            scaleTransition.setToY(1.02);
            scaleTransition.play();
        });

        loginButton.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), loginButton);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required");
            return;
        }

        try {
            Optional<User> user = userService.authenticate(email, password);

            if (user.isPresent()) {
                App.setRoot("Main");
                System.out.println("Login made by success");
            } else {
                System.out.println("Login failed: Invalid credentials");
            }
        } catch (IOException e) {
            System.err.println("Fatal Login Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleForgotPassword() {
        System.out.println("Forgot password clicked");
    }

    @FXML
    private void navigateToRegister() {
        try {
            App.setRoot("Register");
        } catch (IOException e) {
            System.err.println("Error loading Register view: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
