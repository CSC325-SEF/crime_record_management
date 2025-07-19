package com.example.crimerecord.models;

import java.util.Date;

public class FIR {
    private String firId;
    private String complaintId;
    private String officerId;  // Changed officeId to officerId if that makes sense
    private Date createAt;
    private Date updateAt;
    private String remarks;
    private String status;

    public FIR() {
    }

    public FIR(String firId, String complaintId, String officerId, Date createAt, String remarks, String status) {
        this.firId = firId;
        this.complaintId = complaintId;
        this.officerId = officerId;
        this.createAt = createAt;
        this.remarks = remarks;
        this.status = status;
    }

    public String getFirId() {
        return firId;
    }

    public void setFirId(String firId) {
        this.firId = firId;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return
     *
     * @Override
     *     public String toString() {
     *         return "FIR{" +
     *                 "firId='" + firId + '\'' +
     *                 ", complaintId='" + complaintId + '\'' +   // <-- added missing comma
     *                 ", officerId='" + officerId + '\'' +
     *                 ", createAt=" + createAt +
     *                 ", updateAt=" + updateAt +
     *                 ", remarks='" + remarks + '\'' +
     *                 ", status='" + status + '\'' +
     *                 '}';
     *     }
     */


}
