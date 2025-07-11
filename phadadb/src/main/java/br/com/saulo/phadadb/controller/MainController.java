package br.com.saulo.phadadb.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainController implements Initializable {

    @FXML private StackPane centralContentPane;
    
    @FXML private ImageView butterflyImage;
    @FXML private Button btnChatHistory;
    @FXML private Button btnGeneratedFiles;
    @FXML private Button btnDatabases;
    @FXML private Button btnSettings;
    @FXML private Button btnMapDatabase;
    @FXML private Button btnAttach;
    @FXML private Button btnSend;
    @FXML private Button btnQuickQuery1;
    @FXML private Button btnQuickQuery2;
    @FXML private Button btnQuickQuery3;
    
    @FXML private ComboBox<String> cbDatabase;
    @FXML private TextField tfChatInput;
    @FXML private Label lblConnectionStatus;
    @FXML private Label dbStatusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playIntroAnimation();
        playButterflyFloatAnimation();

        List<Button> animatedButtons = Arrays.asList(
            btnChatHistory, btnGeneratedFiles, btnDatabases, btnSettings,
            btnMapDatabase, btnAttach, btnSend
        );

        animatedButtons.forEach(this::addHoverScaleAnimation);
    }

    private void playIntroAnimation() {
        centralContentPane.setOpacity(0);
        centralContentPane.setTranslateY(30);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), centralContentPane);
        fadeTransition.setToValue(1);
        
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(800), centralContentPane);
        translateTransition.setToY(0);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        parallelTransition.setInterpolator(Interpolator.EASE_OUT);
        parallelTransition.play();
    }

    private void playButterflyFloatAnimation() {
        TranslateTransition floatAnimation = new TranslateTransition(Duration.millis(4000), butterflyImage);
        floatAnimation.setByY(-10);
        floatAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        floatAnimation.setAutoReverse(true);
        floatAnimation.setInterpolator(Interpolator.EASE_BOTH);
        floatAnimation.play();
    }
    
    private void addHoverScaleAnimation(Node node) {
        Duration duration = Duration.millis(200);
        
        ScaleTransition scaleUp = new ScaleTransition(duration, node);
        scaleUp.setToX(1.05);
        scaleUp.setToY(1.05);
        
        ScaleTransition scaleDown = new ScaleTransition(duration, node);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        node.setOnMouseEntered(event -> scaleUp.playFromStart());
        node.setOnMouseExited(event -> scaleDown.playFromStart());
    }
}