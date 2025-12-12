package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 遅刻連絡テーブル (LATE_TBL).
 * 
 * 社員の遅刻報告を管理する。
 */
@Entity
@Table(name = "LATE_TBL")
public class LateTbl {

    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @Column(name = "CONTACT_DATE", length = 8, nullable = false)
    private String contactDate;

    @Column(name = "CONTACT_TIME", length = 4, nullable = false)
    private String contactTime;

    @Column(name = "REASON", length = 3)
    private String reason;

    @Column(name = "TRAIN_ID", length = 3)
    private String trainId;

    @Column(name = "ETA", length = 4)
    private String eta;

    @Column(name = "INFORMATION", length = 200)
    private String information;

    public LateTbl() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}