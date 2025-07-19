

package com.example.crimerecord.services;


import com.example.crimerecord.models.Complaint;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class ComplaintService {
    private static final String COLLECTION_NAME = "complaints";

    public static boolean fileComplaint(Complaint complaint) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> result = db.collection(COLLECTION_NAME)
                    .document(complaint.getComplaintId())
                    .set(complaint);

            System.out.println("Complaint filed at: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error filing complaint: " + e.getMessage());
            return false;
        }
    }

    public static List<Complaint> getComplaintsByUserId(String userId) {
        List<Complaint> complaints = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference complaintsRef = db.collection(COLLECTION_NAME);
            Query query = complaintsRef.whereEqualTo("userId", userId);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
                complaints.add(doc.toObject(Complaint.class));
            }

            return complaints;
        } catch (Exception e) {
            System.out.println("Error fetching complaints: " + e.getMessage());
            return complaints;
        }
    }
}
