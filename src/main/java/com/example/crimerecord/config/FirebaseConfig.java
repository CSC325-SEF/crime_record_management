package com.example.crimerecord.config;

import com.example.crimerecord.models.User;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class FirebaseConfig {

    private static boolean initialized = false;

    // Define the Firestore collection name for users here:
    private static final String COLLECTION_NAME = "users";

    public static void initialize() {
        if (initialized) return;

        try (InputStream serviceAccount = FirebaseConfig.class.getClassLoader()
                .getResourceAsStream("files/serviceAccountKey.json")) {

            if (serviceAccount == null) {
                throw new IOException("serviceAccountKey.json not found in classpath!");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("Firebase initialized successfully.");
        } catch (IOException e) {
            System.out.println("Failed to initialize Firebase: " + e.getMessage());
        }
    }

    public static Firestore getFirestore() {
        if (!initialized) {
            initialize();
        }
        return FirestoreClient.getFirestore();
    }


}
