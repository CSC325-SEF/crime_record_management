package com.example.crimerecord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    @FXML
    private Button btnLogout, btnManageUsers, btnViewReports, btnSystemSettings;

    @FXML
    private Label messageLabel;

    private String loggedInUserId;
    public void setLoggedInUserId(String id){
        this.loggedInUserId = id;
    }



    @FXML
    public void initialize() {
        System.out.println("AdminDashboardController initialized");

        btnLogout.setOnAction(e -> handleLogout());
        btnManageUsers.setOnAction(e -> openManageUsers());
        btnViewReports.setOnAction(e -> openViewReports());
        btnSystemSettings.setOnAction(e -> openSystemSettings());
    }

    private void handleLogout() {
        try {
            Main.setRoot("/files/Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to logout.");
        }
    }

    private void openManageUsers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/ManageCases.fxml"));
            Parent root = loader.load();

            ManageCaseController controller = loader.getController();
            controller.setLoggedInUserId(loggedInUserId);

            Stage stage = new Stage();
            stage.setTitle("Manage Cases");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open Manage Cases.");
        }
    }


    private void openViewReports() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/ViewReports.fxml"));
            Parent root = loader.load();

            // Optional: set logged in user if the controller needs it
            ViewReportsController controller = loader.getController();
            controller.setLoggedInUserId(loggedInUserId);

            Stage stage = new Stage();
            stage.setTitle("View Reports");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open Reports.");
        }
    }


    private void openSystemSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/SystemSettings.fxml"));
            Parent root = loader.load();

            SettingsController controller = loader.getController();
            controller.setLoggedInUserId(loggedInUserId);  // optional, if needed

            Stage stage = new Stage();
            stage.setTitle("System Settings");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open System Settings.");
        }
    }

}
