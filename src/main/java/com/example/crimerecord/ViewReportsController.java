package com.example.crimerecord;

import com.example.crimerecord.models.Report;
import com.example.crimerecord.services.ReportService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViewReportsController {

    @FXML
    private TableView<Report> reportTable;
    @FXML private TableColumn<Report, String> colReportId;
    @FXML private TableColumn<Report, String> colCaseId;
    @FXML private TableColumn<Report, String> colOfficer;
    @FXML private TableColumn<Report, String> colSummary;
    @FXML private TableColumn<Report, String> colDate;

    @FXML private Button btnView, btnRefresh, btnExport, btnPrint;
    @FXML private Label statusLabel;

    private String loggedInUserId;
    public void setLoggedInUserId(String id){
        this.loggedInUserId = id;
    }

    @FXML
    public void initialize() {
        bindColumns();
        loadReports();

        btnRefresh.setOnAction(e -> loadReports());
        btnView.setOnAction(e -> handleViewDetails());
        btnExport.setOnAction(e -> handleExportToPDF());
        btnPrint.setOnAction(e -> handlePrint());
    }

    private void bindColumns() {
        colReportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        colCaseId.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        colOfficer.setCellValueFactory(new PropertyValueFactory<>("officerName"));
        colSummary.setCellValueFactory(new PropertyValueFactory<>("summary"));

        colDate.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDate() != null) {
                String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cellData.getValue().getDate());
                return new SimpleStringProperty(formatted);
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
    }

    private void loadReports() {
        try {
            List<Report> reports = ReportService.getAllReports();
            reportTable.getItems().setAll(reports);
            statusLabel.setText("Reports loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Failed to load reports.");
        }
    }

    private void handleViewDetails() {
        Report selected = reportTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Please select a report to view.");
            return;
        }

        Alert details = new Alert(Alert.AlertType.INFORMATION);
        details.setTitle("Report Details");
        details.setHeaderText("Report ID: " + selected.getReportId());
        details.setContentText(
                "Case ID: " + selected.getCaseId() + "\n" +
                        "Officer: " + selected.getOfficerName() + "\n" +
                        "Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(selected.getDate()) + "\n\n" +
                        "Summary:\n" + selected.getSummary()
        );
        details.showAndWait();
    }

    private void handleExportToPDF() {
        // Placeholder logic for PDF export
        statusLabel.setText("Export to PDF feature coming soon.");
    }

    private void handlePrint() {
        // Placeholder logic for printing
        statusLabel.setText("Print feature coming soon.");
    }
}
