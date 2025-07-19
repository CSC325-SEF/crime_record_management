package com.example.crimerecord;

import com.example.crimerecord.models.FIR;
import com.example.crimerecord.services.FIRService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FIRController {
    @FXML
    private TableView<FIR> firTable;

    @FXML
    private TableColumn<FIR, String> colFIRId;

    @FXML
    private TableColumn<FIR, String> colComplaintId;

    @FXML
    private TableColumn<FIR, String> colOfficerId;

    @FXML
    private TableColumn<FIR, String> colStatus;

    @FXML
    private TableColumn<FIR, String> colCreateAt;

    @FXML
    private TableColumn<FIR, String> colRemarks;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        colFIRId.setCellValueFactory(new PropertyValueFactory<>("firId"));
        colComplaintId.setCellValueFactory(new PropertyValueFactory<>("complaintId"));
        colOfficerId.setCellValueFactory(new PropertyValueFactory<>("officerId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        // Custom converter for Date to formatted string
        colCreateAt.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCreateAt() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return new javafx.beans.property.SimpleStringProperty(sdf.format(cellData.getValue().getCreateAt()));
            } else {
                return new javafx.beans.property.SimpleStringProperty("N/A");
            }
        });

        loadFIRs();
    }

    private void loadFIRs() {
        try {
            List<FIR> firList = FIRService.getAllFIRs();
            firTable.getItems().setAll(firList);
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading FIRs.");
        }
    }

    private static final String COLLECTION_NAME = "firs";

    public static List<FIR> getFIRsByOfficerId(String officerId) {
        List<FIR> firList = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                    .whereEqualTo("officerId", officerId)
                    .get();

            for (DocumentSnapshot doc : future.get().getDocuments()) {
                firList.add(doc.toObject(FIR.class));
            }
            return firList;
        } catch (Exception e) {
            System.out.println("Error fetching FIRs: " + e.getMessage());
            return firList;
        }
    }

}
