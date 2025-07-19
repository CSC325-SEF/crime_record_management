package com.example.crimerecord;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class SplashScreenController {

    @FXML
    public void initialize() {
        // Delay before switching to next screen
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            try {
                Main.setRoot("/files/Register.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                Platform.exit();
            }
        });
        delay.play();
    }

}
