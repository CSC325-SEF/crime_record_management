package com.example.crimerecord;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
// Reference to the Label in the FXML file with fx:id="welcomeText"
public class HelloController {
    @FXML
    private Label welcomeText;
    // It sets the label text to a welcome message
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}