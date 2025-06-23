package br.com.saulo.phadadb;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"));

        String cssPath = "/br/com/saulo/phadadb/css/login-style.css";
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
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