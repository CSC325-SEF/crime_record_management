package com.example.crimerecord.models;

import java.util.Date;
import java.util.List;

public class Criminal {
    private String criminalId;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private List<String> crimes; // List of crime descriptions
    private List<Date> offenseDates;
    private String punishment;
    private String status; // e.g., In Custody, Released, Wanted

    public Criminal() {
        // Firestore requires a no-argument constructor
    }

    public Criminal(String criminalId, String name, String gender, Date dateOfBirth,
                    String address, List<String> crimes, List<Date> offenseDates,
                    String punishment, String status) {
        this.criminalId = criminalId;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.crimes = crimes;
        this.offenseDates = offenseDates;
        this.punishment = punishment;
        this.status = status;
    }

    // Getters and Setters
    public String getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<String> crimes) {
        this.crimes = crimes;
    }

    public List<Date> getOffenseDates() {
        return offenseDates;
    }

    public void setOffenseDates(List<Date> offenseDates) {
        this.offenseDates = offenseDates;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    /**
     *
     * @return

    @Override
    public String toString() {
        return "Criminal{" +
                "criminalID='" + criminalId + '\'' +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                '}';

    }

    */

}
