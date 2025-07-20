package com.example.crimerecord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
// FXML-linked UI elements: buttons for navigation and a label for messages
public class OfficerDashboardController {
    @FXML
    private Button btnLogout, btnManageCases, btnViewAssignments, btnReportStatus;

    @FXML
    private Label messageLabel;

    private String loggedInUserId;

    // Stores the ID of the officer currently logged in
    public void setLoggedInUserId(String id){
        this.loggedInUserId = id;
    }
// Called automatically when the FXML is loaded

    @FXML
    public void initialize() {
        System.out.println("OfficerDashboardController initialized");

        btnLogout.setOnAction(e -> handleLogout());
        btnManageCases.setOnAction(e -> openManageCases());
        btnViewAssignments.setOnAction(e -> openViewAssignments());
        btnReportStatus.setOnAction(e -> openReportStatus());
    }

    private void handleLogout() {
        try {
            Main.setRoot("/files/Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to logout.");
        }
    }

    private void openManageCases() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/ManageCases.fxml"));
            Parent root = loader.load();

            // Set the user ID on the complaint form controller
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
    // Opens the View Assignments window and passes in the officer's ID
    private void openViewAssignments() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/ViewAssignments.fxml"));
            Parent root = loader.load();
            ViewAssignmentsController controller = loader.getController();
            controller.setLoggedInUserId(loggedInUserId);

            Stage stage = new Stage();
            stage.setTitle("View Assignments");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open View Assignments.");
        }
    }
    // Opens the Report Status window and passes in the officer's ID
    private void openReportStatus() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/ViewReports.fxml"));
            Parent root = loader.load();
            ViewReportsController controller = loader.getController();
            controller.setLoggedInUserId(loggedInUserId);

            Stage stage = new Stage();
            stage.setTitle("Report Status");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open Report Status.");
        }
    }
}
