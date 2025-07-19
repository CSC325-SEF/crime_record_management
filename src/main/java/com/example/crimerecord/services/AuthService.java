/**
 *
 *
 */

package com.example.crimerecord.services;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AuthService {

    private static final String API_KEY = "AIzaSyBtBkTcggKKb6IAHpCN320jo94ZoRVTM2o"; // replace with your key

    public static String signUpWithEmailPassword(String email, String password) throws Exception {
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;

        String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        return response;
    }

    public static String signInWithEmailPassword(String email, String password) throws Exception {
        String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;

        String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        return response;
    }
}
