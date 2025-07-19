


package com.example.crimerecord.services;


import com.example.crimerecord.models.Criminal;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class CriminalService {
    private static final String COLLECTION_NAME = "criminals";

    public static boolean saveCriminal(Criminal criminal) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> result = db.collection(COLLECTION_NAME)
                    .document(criminal.getCriminalId())
                    .set(criminal);

            System.out.println("Criminal saved at: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error saving criminal: " + e.getMessage());
            return false;
        }
    }

    public static Criminal getCriminalById(String id) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
            DocumentSnapshot document = docRef.get().get();

            if (document.exists()) {
                return document.toObject(Criminal.class);
            } else {
                System.out.println("Criminal not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error retrieving criminal: " + e.getMessage());
            return null;
        }
    }

    public static List<Criminal> getAllCriminals() {
        List<Criminal> criminals = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
            for (DocumentSnapshot doc : future.get().getDocuments()) {
                criminals.add(doc.toObject(Criminal.class));
            }
            return criminals;
        } catch (Exception e) {
            System.out.println("Error fetching criminals: " + e.getMessage());
            return criminals;
        }
    }
}
