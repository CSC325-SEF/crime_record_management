package com.example.crimerecord.models;

public class User {
    private String uid;
    private String fullName;
    private String email;
    private UserRole role;

    public User() {
    }

    public User(String uid, String fullName, String email, UserRole role) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
