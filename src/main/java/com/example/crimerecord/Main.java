package com.example.crimerecord;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
// Shared reference to the current scene and stage
public class Main extends Application {
    public static Scene scene;
    public static Stage primaryStage;
    // Entry point for JavaFX application
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;

        Parent splashRoot = loadFXML("/files/SplashScreen.fxml");
        scene = new Scene(splashRoot, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crime Record Management System");
        primaryStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            try {

                setRoot("/files/CrimePortal.fxml");
                primaryStage.centerOnScreen();


            } catch (IOException e) {
                e.printStackTrace();
                primaryStage.close();
            }
        });
        delay.play();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = loader.load();

        Scene newScene = new Scene(root, 1200, 800);
        primaryStage.setScene(newScene);
        primaryStage.show();
        primaryStage.toFront();

        scene = newScene;

        System.out.println(" Scene switched to: " + fxml);
    }

    // Loads an FXML layout and returns the root node
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        return fxmlLoader.load();
    }
    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
