package com.example.crimerecord;

import com.example.crimerecord.models.Case;
import com.example.crimerecord.services.CaseService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class ManageCaseController {
    // Table and columns for displaying case data
    @FXML
    private TableView<Case> caseTable;
    @FXML private TableColumn<Case, String> colCaseId;
    @FXML private TableColumn<Case, String> colCriminalId;
    @FXML private TableColumn<Case, String> colOfficerId;
    @FXML private TableColumn<Case, String> colStatus;
    @FXML private TableColumn<Case, String> colDate;
    @FXML private TableColumn<Case, String> colRemarks;
    // Buttons and message label
    @FXML private Button btnAdd, btnEdit, btnDelete, btnRefresh;
    @FXML private Label messageLabel;

    private String loggedInUserId;

    public void setLoggedInUserId(String id) {
        this.loggedInUserId = id;
    }
    // Called when FXML is initialized
    @FXML
    public void initialize() {
        bindTableColumns();
        loadCases();

        btnRefresh.setOnAction(e -> loadCases());
        btnDelete.setOnAction(e -> handleDelete());
        // You can add logic for btnAdd and btnEdit later
    }

    // Set up column bindings for the TableView
    private void bindTableColumns() {
        colCaseId.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        colCriminalId.setCellValueFactory(new PropertyValueFactory<>("criminalId"));
        colOfficerId.setCellValueFactory(new PropertyValueFactory<>("officerId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        colDate.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDate() != null) {
                String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cellData.getValue().getDate());
                return new SimpleStringProperty(formatted);
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
    }
    //display all cases in the table

    private void loadCases() {
        try {
            List<Case> caseList = CaseService.getAllCases();
            caseTable.getItems().setAll(caseList);
            messageLabel.setText("Cases loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading cases.");
        }
    }
    // Handle case deletion when the Delete button is clicked

    private void handleDelete() {
        Case selected = caseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Please select a case to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Case");
        confirm.setContentText("Are you sure you want to delete this case?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                CaseService.deleteCase(selected.getCaseId());
                loadCases();
                messageLabel.setText("Case deleted successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                messageLabel.setText("Failed to delete case.");
            }
        }
        // initialize table
    }
}
