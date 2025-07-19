package com.example.crimerecord;

import com.example.crimerecord.models.User;
import com.example.crimerecord.models.UserRole;
import com.example.crimerecord.services.FirestoreService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage splashStage;
    private UserRole selectedRole;

    public void setStage(Stage stage) {
        this.splashStage = stage;
    }

    public void setUserRole(UserRole role) {
        this.selectedRole = role;
        System.out.println("Login screen for: " + role);

        if (messageLabel != null) {
            messageLabel.setText("Login as " + role.name());
            messageLabel.setStyle("-fx-text-fill: #333;");
        }
    }

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private void initialize() {
        messageLabel.setText("");
        loginButton.setDisable(true);

        emailField.textProperty().addListener((obs, oldText, newText) -> validateInput());
        passwordField.textProperty().addListener((obs, oldText, newText) -> validateInput());
    }

    private void validateInput() {
        boolean emailFilled = !emailField.getText().trim().isEmpty();
        boolean passwordFilled = !passwordField.getText().trim().isEmpty();
        loginButton.setDisable(!(emailFilled && passwordFilled));
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter email and password.");
            return;
        }

        loginButton.setDisable(true);
        messageLabel.setText("Logging in...");

        new Thread(() -> {
            try {
                String uidOrError = FirestoreService.loginUser(email, password);
                Platform.runLater(() -> {
                    if (uidOrError != null && !uidOrError.startsWith("Error")) {
                        try {
                            User user = FirestoreService.getUserDetails(uidOrError);
                            if (user != null) {
                                UserRole role = user.getRole();

                                // *** Check for role mismatch ***
                                if (role != selectedRole) {
                                    messageLabel.setStyle("-fx-text-fill: red;");
                                    messageLabel.setText("Role mismatch: You selected " + selectedRole +
                                            " but your account role is " + role + ".");
                                    loginButton.setDisable(false);
                                    return;
                                }

                                messageLabel.setStyle("-fx-text-fill: green;");
                                messageLabel.setText("Login successful! Redirecting...");

                                switch (role) {
                                    case CIVILIAN -> {
                                        try {
                                            Main.setRoot("/files/CivilianDashboard.fxml");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    case OFFICER -> {
                                        try {
                                            Main.setRoot("/files/OfficerDashboard.fxml");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    case ADMINISTRATOR -> {
                                        try {
                                            Main.setRoot("/files/AdminDashboard.fxml");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    default -> {
                                        messageLabel.setText("Unknown user role.");
                                        loginButton.setDisable(false);
                                    }
                                }
                            } else {
                                messageLabel.setStyle("-fx-text-fill: red;");
                                messageLabel.setText("Failed to fetch user details.");
                                loginButton.setDisable(false);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            messageLabel.setStyle("-fx-text-fill: red;");
                            messageLabel.setText("Failed to load dashboard.");
                            loginButton.setDisable(false);
                        }
                    } else {
                        messageLabel.setStyle("-fx-text-fill: red;");
                        messageLabel.setText(uidOrError);
                        loginButton.setDisable(false);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    messageLabel.setStyle("-fx-text-fill: red;");
                    messageLabel.setText("Failed to connect to Firebase.");
                    loginButton.setDisable(false);
                });
            }
        }).start();
    }

    @FXML
    private void openRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/Register.fxml"));
            Parent root = loader.load();

            RegisterController registerController = loader.getController();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            Main.scene = newScene;
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load registration screen.");
        }
    }

    @FXML
    private void clearFields() {
        emailField.clear();
        passwordField.clear();
        messageLabel.setText("");
        loginButton.setDisable(true);
    }
}
