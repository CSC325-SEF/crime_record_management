package com.example.crimerecord.services;

import com.example.crimerecord.models.Report;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private static final String COLLECTION_NAME = "reports";

    public static List<Report> getAllReports() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Report> reportList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Report report = doc.toObject(Report.class);
            report.setReportId(doc.getId());

            // Handle possible nulls from Firestore
            if (doc.contains("date") && doc.get("date") instanceof Timestamp) {
                report.setDate(((Timestamp) doc.get("date")).toDate());
            }

            reportList.add(report);
        }

        return reportList;
    }

    public static void addReport(Report newReport) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).add(newReport);
    }

    public static void updateReport(String reportId, Report updatedReport) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(reportId).set(updatedReport);
    }

    public static void deleteReport(String reportId) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(reportId).delete();
    }
}
