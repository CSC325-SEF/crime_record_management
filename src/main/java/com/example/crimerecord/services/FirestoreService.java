package com.example.crimerecord.services;

import com.example.crimerecord.config.FirebaseConfig;
import com.example.crimerecord.models.User;
import com.example.crimerecord.models.UserRole;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class FirestoreService {
    private static final String COLLECTION_NAME = "users";
    private static final String API_KEY = "AIzaSyBtBkTcggKKb6IAHpCN320jo94ZoRVTM2o"; // keep secure

    public static String registerUser(String email, String password) throws IOException {
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;
        String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password
        );

        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        int status = conn.getResponseCode();
        InputStream responseStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(responseStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        String responseBody = response.toString();
        if (status == 200) {
            String uid = extractUidFromJson(responseBody);
            if (uid != null && !uid.isEmpty()) {
                return uid;
            } else {
                return "Error: UID missing in registration response.";
            }
        } else {
            return "Error: " + responseBody;
        }

    }
    public static void saveUserDetails(User user) {
        try {
            Firestore db = FirebaseConfig.getFirestore();
            var future = db.collection(COLLECTION_NAME).document(user.getUid()).set(user);
            var result = future.get();  // wait for completion
            System.out.println("User data saved to Firestore at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Failed to save user data: " + e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }



    // Extract Firebase UID from response JSON
    private static String extractUidFromJson(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            if (obj.has("localId")) {
                return obj.getString("localId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    public static String loginUser(String email, String password) throws IOException {
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
        String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password
        );

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        int status = conn.getResponseCode();
        InputStream responseStream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(responseStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
        }

        String responseBody = response.toString();

        try {
            JSONObject json = new JSONObject(responseBody);
            if (status == 200) {
                // Successful login, extract UID (localId)
                if (json.has("localId")) {
                    return json.getString("localId");
                } else {
                    return "Error: UID missing in response.";
                }
            } else {
                // On error, extract Firebase error message
                if (json.has("error")) {
                    JSONObject error = json.getJSONObject("error");
                    if (error.has("message")) {
                        return "Error: " + error.getString("message");
                    }
                }
                // Fallback error
                return "Error: " + responseBody;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Failed to parse response.";
        }
    }


    public static User getUserDetails(String uid) {
        try {
            Firestore db = FirebaseConfig.getFirestore();
            ApiFuture<DocumentSnapshot> future = db.collection(COLLECTION_NAME).document(uid).get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                String userId = document.getString("uid");
                if (userId == null || userId.isEmpty()) {
                    userId = document.getId();
                }

                String fullName = document.getString("fullName");
                String email = document.getString("email");
                String roleStr = document.getString("role");

                UserRole role = UserRole.CIVILIAN; // default
                if (roleStr != null) {
                    try {
                        role = UserRole.valueOf(roleStr.toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        System.err.println("Unknown role in Firestore: " + roleStr);
                    }
                }

                return new User(userId, fullName, email, role);
            } else {
                System.err.println("No such user with uid: " + uid);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return null;
    }




}
