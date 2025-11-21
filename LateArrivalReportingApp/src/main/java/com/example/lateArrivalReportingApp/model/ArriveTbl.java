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

    @Column(name = "TRAIN_ID_1", length = 3)
    private String trainId1;

    @Column(name = "DELAY_1", length = 3)
    private String delay1;

    @Column(name = "TRAIN_ID_2", length = 3)
    private String trainId2;

    @Column(name = "DELAY_2", length = 3)
    private String delay2;

    @Column(name = "TRAIN_ID_3", length = 3)
    private String trainId3;

    @Column(name = "DELAY_3", length = 3)
    private String delay3;

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

    public String getTrainId1() {
        return trainId1;
    }

    public void setTrainId1(String trainId1) {
        this.trainId1 = trainId1;
    }

    public String getDelay1() {
        return delay1;
    }

    public void setDelay1(String delay1) {
        this.delay1 = delay1;
    }

    public String getTrainId2() {
        return trainId2;
    }

    public void setTrainId2(String trainId2) {
        this.trainId2 = trainId2;
    }

    public String getDelay2() {
        return delay2;
    }

    public void setDelay2(String delay2) {
        this.delay2 = delay2;
    }

    public String getTrainId3() {
        return trainId3;
    }

    public void setTrainId3(String trainId3) {
        this.trainId3 = trainId3;
    }

    public String getDelay3() {
        return delay3;
    }

    public void setDelay3(String delay3) {
        this.delay3 = delay3;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}