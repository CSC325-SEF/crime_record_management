package com.example.crimerecord.services;

import com.example.crimerecord.models.Case;
import com.example.crimerecord.models.Setting;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SettingService {
    private static final String COLLECTION_NAME = "settings";

    public static List<Setting> getAllSettings() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Setting> settingsList = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            Setting setting = doc.toObject(Setting.class);
            // Optionally, set the document ID if needed:
            // setting.setId(doc.getId());
            settingsList.add(setting);
        }
        return settingsList;
    }

    // Get single setting by key (assumes document ID = key)
    public static Setting getSettingByKey(String key) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> future = db.collection(COLLECTION_NAME).document(key).get();
        QueryDocumentSnapshot document = (QueryDocumentSnapshot) future.get();
        if (document.exists()) {
            return document.toObject(Setting.class);
        }
        return null;
    }

    // Save or update setting
    public static void saveSetting(Setting setting) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        // Use setting key as document ID
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(setting.getKey());
        docRef.set(setting).get();  // synchronous wait
    }

    // Delete setting by key
    public static void deleteSetting(String key) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME).document(key).delete().get();
    }
}
