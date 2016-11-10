package ru.ifmo.ctddev.segal.hw4;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            URL mainViewFXML = getClass().getClassLoader().getResource("MainView.fxml");
            if (mainViewFXML == null) {
                throw new IllegalStateException("There is no MainView.fxml in resources");
            }
            Parent root = FXMLLoader.load(mainViewFXML);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Lorenz system");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}