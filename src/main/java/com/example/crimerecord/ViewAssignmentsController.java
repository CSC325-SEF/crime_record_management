package com.example.crimerecord;

import com.example.crimerecord.models.Assignment;
import com.example.crimerecord.services.AssignmentService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ViewAssignmentsController {
    // Table and columns to display assignment data
    @FXML private TableView<Assignment> assignmentTable;
    @FXML private TableColumn<Assignment, String> colAssignmentId;
    @FXML private TableColumn<Assignment, String> colCaseId;
    @FXML private TableColumn<Assignment, String> colOfficer;
    @FXML private TableColumn<Assignment, String> colDateAssigned;
    @FXML private TableColumn<Assignment, String> colStatus;
    // Action buttons and status label
    @FXML private Button btnView;
    @FXML private Button btnRefresh;
    @FXML private Button btnExport;
    @FXML private Button btnPrint;
    @FXML private Label statusLabel;
    // Setter used to pass in the logged-in officer/admin ID
    private String loggedInUserId;
    public void setLoggedInUserId(String id){
        this.loggedInUserId = id;
    }
    // Called after the FXML is loaded
    @FXML
    public void initialize() {
        bindColumns(); // You forgot to call this
        loadAssignments();


    }
    // Maps each table column to its corresponding property in Assignment
    private void bindColumns() {
        colAssignmentId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAssignmentId()));
        colCaseId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCaseId()));
        colOfficer.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOfficerName()));
        colStatus.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));
        colDateAssigned.setCellValueFactory(data -> {
            if (data.getValue().getDateAssigned() != null) {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(data.getValue().getDateAssigned());
                return new SimpleStringProperty(date);
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
    }
    // Loads all assignments from the service and populates the TableView

    private void loadAssignments() {
        try {
            List<Assignment> assignments = AssignmentService.getAllAssignments();
            assignmentTable.getItems().setAll(assignments);
            statusLabel.setText("Assignments loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Failed to load assignments.");
        }
    }
    // Shows a pop-up with full details of the selected assignment

    private void handleViewDetails() {
        Assignment selected = assignmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Please select an assignment to view.");
            return;
        }
        // Show assignment details in a JavaFX information dialog

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Assignment Details");
        alert.setHeaderText("Assignment ID: " + selected.getAssignmentId());
        alert.setContentText(
                "Case ID: " + selected.getCaseId() + "\n" +
                        "Officer: " + selected.getOfficerName() + "\n" +
                        "Date Assigned: " + (selected.getDateAssigned() != null
                        ? new SimpleDateFormat("yyyy-MM-dd").format(selected.getDateAssigned())
                        : "N/A") + "\n" +
                        "Status: " + selected.getStatus()
        );
        alert.showAndWait();
    }

    private void handleExport() {
        // TODO: Implement real export functionality
        statusLabel.setText("Export feature coming soon.");
    }

    private void handlePrint() {
        // TODO: Implement real print functionality
        statusLabel.setText("Print feature coming soon.");
    }
}
