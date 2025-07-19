package com.example.crimerecord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CivilianDashboardController {

    @FXML
    private Button btnLogout, btnFileComplaint, btnViewFIRs, btnCriminalInfo;

    @FXML
    private Label messageLabel;

    private String loggedInUserId;
    public void setLoggedInUserId(String id){
        this.loggedInUserId = id;
    }


    @FXML
    public void initialize() {
        System.out.println("CivilianDashboardController initialized");
        btnLogout.setOnAction(e -> handleLogout());
        btnFileComplaint.setOnAction(e -> openComplaintForm());
       btnViewFIRs.setOnAction(e -> openFIRView());
       btnCriminalInfo.setOnAction(e -> openCriminalInfo());
    }

    private void handleLogout() {
        try {
            Main.setRoot("/files/login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to logout.");
        }
    }

    private void openComplaintForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/ComplaintForm.fxml"));
            Parent root = loader.load();

            // Set the user ID on the complaint form controller
            ComplaintController controller = loader.getController();
            controller.setUserId(loggedInUserId);

            Stage stage = new Stage();
            stage.setTitle("File Complaint");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFIRView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/FIRView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("View FIRs");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to load FIRs.");
        }
    }

    private void openCriminalInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/CriminalInfo.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("View FIRs");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open criminal info.");
        }


    }

}
