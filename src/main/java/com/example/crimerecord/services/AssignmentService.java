package com.example.crimerecord.services;

import com.example.crimerecord.models.Assignment;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class AssignmentService {

    private static final String COLLECTION_NAME = "assignments";

    /**
     * Retrieve all assignments from Firestore.
     */
    public static List<Assignment> getAllAssignments() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Assignment> assignments = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            Assignment assignment = doc.toObject(Assignment.class);
            assignment.setAssignmentId(doc.getId()); // Set Firestore document ID
            assignments.add(assignment);
        }

        return assignments;
    }

    /**
     * Get assignment by ID.
     */
    public static Assignment getAssignmentById(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot document = db.collection(COLLECTION_NAME).document(id).get().get();

        if (document.exists()) {
            Assignment assignment = document.toObject(Assignment.class);
            assignment.setAssignmentId(document.getId());
            return assignment;
        }
        return null;
    }

    /**
     * Save or update an assignment.
     */
    public static void saveAssignment(Assignment assignment) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(assignment.getAssignmentId())
                .set(assignment);
    }

    /**
     * Delete an assignment.
     */
    public static void deleteAssignment(String id) {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(id).delete();
    }
}
