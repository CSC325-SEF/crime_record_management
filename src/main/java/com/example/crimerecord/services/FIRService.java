

package com.example.crimerecord.services;



import com.example.crimerecord.models.FIR;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class FIRService {
    private static final String COLLECTION_NAME = "firs";

    public static boolean createFIR(FIR fir) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> result = db.collection(COLLECTION_NAME)
                    .document(fir.getFirId())
                    .set(fir);

            System.out.println("FIR created at: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error creating FIR: " + e.getMessage());
            return false;
        }
    }

    public static List<FIR> getAllFIRs() {
        List<FIR> firList = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();

            for (DocumentSnapshot doc : future.get().getDocuments()) {
                firList.add(doc.toObject(FIR.class));
            }

            return firList;
        } catch (Exception e) {
            System.out.println("Error fetching FIRs: " + e.getMessage());
            return firList;
        }
    }

    public static FIR getFIRById(String firId) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference docRef = db.collection(COLLECTION_NAME).document(firId);
            DocumentSnapshot document = docRef.get().get();

            if (document.exists()) {
                return document.toObject(FIR.class);
            } else {
                System.out.println("FIR not found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error retrieving FIR: " + e.getMessage());
            return null;
        }
    }
}

