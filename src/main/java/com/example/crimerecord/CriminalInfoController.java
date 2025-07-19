package com.example.crimerecord;

import com.example.crimerecord.models.Criminal;
import com.example.crimerecord.services.CriminalService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CriminalInfoController {
    @FXML
    private TextField criminalIdField;
    @FXML private TextField nameField;
    @FXML private TextField genderField;
    @FXML private TextField dobField;
    @FXML private TextField addressField;
    @FXML private TextArea crimesField;
    @FXML private TextArea datesField;
    @FXML private TextField punishmentField;
    @FXML private TextField statusField;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private void onLoadClicked() {
        String id = criminalIdField.getText().trim();
        if (id.isEmpty()) {
            showAlert("Please enter a Criminal ID.");
            return;
        }

        Criminal criminal = CriminalService.getCriminalById(id);
        if (criminal == null) {
            showAlert("Criminal not found.");
            return;
        }

        populateFields(criminal);
    }

    private void populateFields(Criminal criminal) {
        nameField.setText(criminal.getName());
        genderField.setText(criminal.getGender());

        Date dob = criminal.getDateOfBirth();
        dobField.setText(dob != null ? dateFormat.format(dob) : "");

        addressField.setText(criminal.getAddress());

        List<String> crimes = criminal.getCrimes();
        crimesField.setText(crimes != null ? String.join(", ", crimes) : "");

        List<Date> offenseDates = criminal.getOffenseDates();
        datesField.setText(offenseDates != null
                ? offenseDates.stream().map(dateFormat::format).collect(Collectors.joining(", "))
                : "");

        punishmentField.setText(criminal.getPunishment());
        statusField.setText(criminal.getStatus());
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
