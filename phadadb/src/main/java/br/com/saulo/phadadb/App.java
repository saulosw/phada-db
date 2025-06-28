package br.com.saulo.phadadb;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(new Pane(), 1400, 900);
        stage.setScene(scene);

        setRoot("Login");

        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxmlFileName) throws IOException {
        Parent root = loadFXML(fxmlFileName);
        scene.setRoot(root);

        scene.getStylesheets().clear();

        String cssPath = "/br/com/saulo/phadadb/css/" + fxmlFileName.toLowerCase() + "-style.css";
        URL cssUrl = App.class.getResource(cssPath);

        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("Warning: CSS file not found: " + cssPath);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        String resourcePath = "/br/com/saulo/phadadb/view/" + fxml + ".fxml";
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(resourcePath));
        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}