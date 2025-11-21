package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

/**
 * 電車マスタ.
 *
 * システムで使用する路線を管理する。
 * 
 */
@Entity
@Table(name = "TRAIN_MST")
public class TrainMst {

    @Id
    @Column(name = "TRAIN_ID", length = 3, nullable = false)
    private String trainId;

    @Column(name = "COMPANY", length = 30, nullable = false)
    private String company;

    @Column(name = "RAILWAY", length = 30, nullable = false)
    private String railway;

    @Column(name = "REMARK", length = 100)
    private String remark;

    public TrainMst() {
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRailway() {
        return railway;
    }

    public void setRailway(String railway) {
        this.railway = railway;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
