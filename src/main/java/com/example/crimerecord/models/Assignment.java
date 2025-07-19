package com.example.crimerecord.models;

import java.util.Date;

public class Assignment {
    private String assignmentId;
    private String caseId;
    private String officerName;
    private Date dateAssigned;
    private String status;

    public String getAssignmentId() {
        return assignmentId;
    }
    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }
    public String getCaseId() {
        return caseId;
    }
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    public String getOfficerName() {
        return officerName;
    }
    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }
    public Date getDateAssigned() {
        return dateAssigned;
    }
    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }
    public String getStatus() {
        return status;

    }
    public void setStatus(String status) {
        this.status = status;
    }

}
