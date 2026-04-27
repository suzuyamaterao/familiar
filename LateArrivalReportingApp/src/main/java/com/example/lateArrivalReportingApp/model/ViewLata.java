package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "view_lata")

@Immutable // 更新不可（重要）
@IdClass(ViewLataId.class) // 複合キー
public class ViewLata {

    @Id
    @Column(name = "emp_id")
    private String empId;

    @Id
    @Column(name = "contact_date")
    private String contactDate;

    @Column(name = "unit_no")
    private String unitNo;

    @Column(name = "unit_nm")
    private String unitNm;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "name")
    private String name;

    @Column(name = "reason")
    private String reason;

    @Column(name = "reason_nm")
    private String reasonNm;

    @Column(name = "late_contact_time")
    private String lateContactTime;

    @Column(name = "train_id")
    private String trainId;

    @Column(name = "railway")
    private String railway;

    @Column(name = "eta")
    private String eta;

    @Column(name = "late_information")
    private String lateInformation;

    @Column(name = "late_umu")
    private String lateUmu;

    @Column(name = "late_umu_nm")
    private String lateUmuNm;

    @Column(name = "arr_contact_time")
    private String arrContactTime;

    @Column(name = "late_time")
    private String lateTime;

    @Column(name = "arrive_time")
    private String arriveTime;

    @Column(name = "delay")
    private String delay;

    @Column(name = "arr_information")
    private String arrInformation;

    public String getEmpId() {
        return empId;
    }

    public String getContactDate() {
        return contactDate;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public String getUnitNm() {
        return unitNm;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public String getReasonNm() {
        return reasonNm;
    }

    public String getLateContactTime() {
        return lateContactTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getRailway() {
        return railway;
    }

    public String getEta() {
        return eta;
    }

    public String getLateInformation() {
        return lateInformation;
    }

    public String getLateUmu() {
        return lateUmu;
    }

    public String getLateUmuNm() {
        return lateUmuNm;
    }

    public String getArrContactTime() {
        return arrContactTime;
    }

    public String getLateTime() {
        return lateTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public String getDelay() {
        return delay;
    }

    public String getArrInformation() {
        return arrInformation;
    }
}