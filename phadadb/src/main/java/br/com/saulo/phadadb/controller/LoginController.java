package br.com.saulo.phadadb.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.saulo.phadadb.App;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSideImage();
        addEntryAnimations();
        addHoverEffects();
    }
    
    private void loadSideImage() {
        try {
            loginLeftImage.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e1e2e, #2d1b69);");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        System.out.println("Login clicked");
    }
    
    @FXML
    private void handleForgotPassword() {
        System.out.println("Forgot password clicked");
    }
    
    /**
     * NAVEGA para a tela de registro quando o link "Sign up" é clicado.
     */
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
