package com.example.crimerecord.models;

public class Complaint {
    private String complaintId;
    private String userId;
    private String description;
    private String location;
    private String status;


    public Complaint(){}

    public Complaint(String complaintId, String userId, String description, String location, String status) {
        this.complaintId = complaintId;
        this.userId = userId;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public String getComplaintId() {
        return complaintId;
    }
    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
