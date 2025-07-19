package com.example.crimerecord;

import com.example.crimerecord.models.UserRole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CrimePortal {

    @FXML
    private Button btnCivilian;

    @FXML
    private Button btnOfficer;

    @FXML
    private Button btnAdmin;

    @FXML
    public void initialize() {
        btnCivilian.setOnAction(e -> handleRoleSelection(UserRole.CIVILIAN));
        btnOfficer.setOnAction(e -> handleRoleSelection(UserRole.OFFICER));
        btnAdmin.setOnAction(e -> handleRoleSelection(UserRole.ADMINISTRATOR));

    }



    private void handleRoleSelection(UserRole role) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/files/login.fxml"));
            Parent root = loader.load();

            LoginController controller = loader.getController();
            controller.setUserRole(role);

            Stage stage = (Stage) btnCivilian.getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            Main.scene = newScene;
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
