package br.com.saulo.phadadb;

import java.io.IOException;
import java.net.URL;

import br.com.saulo.phadadb.controller.LoginController;
import br.com.saulo.phadadb.controller.RegisterController;
import br.com.saulo.phadadb.dao.UserDAO;
import br.com.saulo.phadadb.dao.impl.UserDataBase;
import br.com.saulo.phadadb.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    private static final UserDAO userDAO = new UserDataBase();
    private static final UserService userService = new UserService(userDAO);

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/br/com/saulo/phadadb/fonts/Inter_24pt-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("/br/com/saulo/phadadb/fonts/Inter_24pt-Bold.ttf"), 10);

        scene = new Scene(new Pane(), 1400, 900);
        stage.setScene(scene);
        setRoot("Login");
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxmlFileName) throws IOException {
        String resourcePath = "/br/com/saulo/phadadb/view/" + fxmlFileName + ".fxml";
        FXMLLoader loader = new FXMLLoader(App.class.getResource(resourcePath));

        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == LoginController.class) {
                return new LoginController(userService);
            } else if (controllerClass == RegisterController.class) {
                return new RegisterController(userService);
            } else {
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    throw new RuntimeException("Failed to create controller: " + controllerClass.getName(), ex);
                }
            }
        });

        Parent root = loader.load();
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

    public static void main(String[] args) {
        launch();
    }
}
