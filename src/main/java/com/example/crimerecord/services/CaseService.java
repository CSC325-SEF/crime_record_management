package com.example.crimerecord.services;

import com.example.crimerecord.models.Case;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class CaseService {
    private static final String COLLECTION_NAME = "cases";

    public static List<Case> getAllCases() throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Case> caseList = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Case criminalCase = doc.toObject(Case.class);
            criminalCase.setCaseId(doc.getId());
            caseList.add(criminalCase);
        }

        return caseList;
    }

    public static void addCase(Case newCase) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).add(newCase);
    }

    public static void updateCase(String caseId, Case updatedCase) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(caseId).set(updatedCase);
    }

    public static void deleteCase(String caseId) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(caseId).delete();
    }
}
