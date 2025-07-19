package com.example.crimerecord;

import com.example.crimerecord.models.User;
import com.example.crimerecord.models.UserRole;
import com.example.crimerecord.services.FirestoreService;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField displayNameField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;
    @FXML
    private ChoiceBox<UserRole> roleChoiceBox;

    @FXML
    private void initialize() {
        roleChoiceBox.getItems().setAll(UserRole.CIVILIAN, UserRole.OFFICER, UserRole.ADMINISTRATOR);
        roleChoiceBox.getSelectionModel().select(UserRole.CIVILIAN);
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String fullName = displayNameField.getText().trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullName.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        UserRole selectedRole = roleChoiceBox.getValue();
        if (selectedRole == null) {
            messageLabel.setText("Please select a role.");
            return;
        }

        new Thread(() -> {
            try {
                String uid = FirestoreService.registerUser(email, password); // returns UID or error

                if (uid != null && !uid.startsWith("Error")) {
                    User newUser = new User(uid, fullName, email, selectedRole);
                    FirestoreService.saveUserDetails(newUser);

                    Platform.runLater(() -> {
                        messageLabel.setStyle("-fx-text-fill: green;");
                        messageLabel.setText("Registration successful! Redirecting...");

                        PauseTransition delay = new PauseTransition(Duration.seconds(2));
                        delay.setOnFinished(e -> openLoginScreen());
                        delay.play();
                    });
                } else {
                    Platform.runLater(() -> {
                        messageLabel.setStyle("-fx-text-fill: red;");
                        messageLabel.setText(uid != null ? uid : "Unknown error occurred.");
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    messageLabel.setStyle("-fx-text-fill: red;");
                    messageLabel.setText("Failed to connect to Firebase.");
                });
            }
        }).start();
    }


    // This version can be called without needing a MouseEvent
    private void openLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Failed to load login screen.");
        }
    }

    @FXML
    private void openLogin(MouseEvent event) {
        openLoginScreen();
    }
}
