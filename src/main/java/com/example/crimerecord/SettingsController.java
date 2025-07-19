package com.example.crimerecord;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class SettingsController {

    @FXML
    private ChoiceBox<String> themeChoiceBox;

    @FXML
    private TextField autoSaveIntervalField;

    @FXML
    private Button btnSaveSettings;

    @FXML
    private Label lblStatus;

    private String loggedInUserId;
    public void setLoggedInUserId(String id) {
        this.loggedInUserId = id;
    }

    @FXML
    public void initialize() {
        // Set default choice box value
        themeChoiceBox.setValue("System Default");

        // Attach event handler
        btnSaveSettings.setOnAction(this::handleSaveSettings);
    }

    private void handleSaveSettings(ActionEvent event) {
        String selectedTheme = themeChoiceBox.getValue();
        String intervalText = autoSaveIntervalField.getText();

        if (intervalText == null || intervalText.isBlank()) {
            lblStatus.setText("Auto Save Interval is required.");
            lblStatus.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            int interval = Integer.parseInt(intervalText);
            if (interval <= 0) {
                lblStatus.setText("Interval must be positive.");
                lblStatus.setStyle("-fx-text-fill: red;");
                return;
            }

            // Simulate saving settings
            lblStatus.setText("Settings saved: Theme = " + selectedTheme + ", Auto Save = " + interval + " mins");
            lblStatus.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            lblStatus.setText("Interval must be a number.");
            lblStatus.setStyle("-fx-text-fill: red;");
        }
    }
}
