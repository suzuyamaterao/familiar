package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 到着連絡テーブル (ARRIVE_TBL).
 * 
 * 社員の到着報告を管理する。
 * 
 */
@Entity
@Table(name = "ARRIVE_TBL")
public class ArriveTbl {

    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @Column(name = "CONTACT_DATE", length = 8, nullable = false)
    private String contactDate;

    @Column(name = "CONTACT_TIME", length = 4, nullable = false)
    private String contactTime;

    @Column(name = "LATE_UMU", length = 3, nullable = false)
    private String lateUmu;

    @Column(name = "LATE_TIME", length = 4)
    private String lateTime;

    @Column(name = "ARRIVE_TIME", length = 4)
    private String arriveTime;

    @Column(name = "TRAIN_ID", length = 3)
    private String trainId;

    @Column(name = "DELAY", length = 3)
    private String delay;

    @Column(name = "INFORMATION", length = 200)
    private String information;

    public ArriveTbl() {
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

    public String getLateUmu() {
        return lateUmu;
    }

    public void setLateUmu(String lateUmu) {
        this.lateUmu = lateUmu;
    }

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId1(String trainId) {
        this.trainId = trainId;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay1(String delay) {
        this.delay = delay;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}