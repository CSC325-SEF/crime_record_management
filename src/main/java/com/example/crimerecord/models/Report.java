package com.example.crimerecord.models;

import java.util.Date;

public class Report {
    private String reportId;
    private String caseId;
    private String officerName;
    private String summary;
    private Date date;


    public Report(){}


    public Report(String reportId, String caseId, String officerName, String summary, Date date) {
        this.reportId = reportId;
        this.caseId = caseId;
        this.officerName = officerName;
        this.summary = summary;
        this.date = date;

    }

    public String getReportId() {
        return reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
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
    public String getSummary() {

        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
