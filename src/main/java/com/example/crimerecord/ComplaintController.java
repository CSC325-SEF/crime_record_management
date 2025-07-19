package com.example.crimerecord;

import com.example.crimerecord.models.Complaint;
import com.example.crimerecord.services.ComplaintService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.UUID;

public class ComplaintController {

    @FXML
    private TextField titleField;
    @FXML private TextArea descriptionField;
    @FXML private TextField locationField;
    @FXML private Button submitButton;
    @FXML private Label messageLabel;

    private String userId;

    @FXML
    public void initialize() {
        submitButton.setOnAction(e -> handleSubmit());
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private void handleSubmit() {
        String title = titleField.getText().trim(); // Optional if you include title in your Firestore model
        String description = descriptionField.getText().trim();
        String location = locationField.getText().trim();

        if (description.isEmpty() || location.isEmpty()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        Complaint complaint = new Complaint();
        complaint.setComplaintId(UUID.randomUUID().toString());
        complaint.setUserId(userId);
        complaint.setDescription(description);
        complaint.setLocation(location);
        complaint.setStatus("Pending");

        boolean success = ComplaintService.fileComplaint(complaint);
        if (success) {
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Complaint submitted successfully!");

            titleField.clear();
            descriptionField.clear();
            locationField.clear();
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Error submitting complaint. Please try again.");
        }
    }
}
