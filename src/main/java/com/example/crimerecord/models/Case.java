package com.example.crimerecord.models;

import java.util.Date;

public class Case {
    private String caseId;
    private String criminalId;
    private String officeId;
    private Date date;
    private  String status;
    private String remarks;


    public Case(){}


    public Case(String caseId, String officeId, Date date, String status, String remarks, String criminalId) {
        this.caseId = caseId;
        this.officeId = officeId;
        this.date = date;
        this.status = status;
        this.officeId = officeId;
        this.date = date;
        this.remarks = remarks;
        this.criminalId = criminalId;
    }

    public String getCaseId() {
        return caseId;

    }
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    public String getOfficeId() {
        return officeId;

    }
    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
