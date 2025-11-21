package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 現場社員テーブル (RELATE_EMP_TBL).
 * 
 * 現場で一緒に稼働している社員を管理する。
 * 
 */
@Entity
@Table(name = "RELATE_EMP_TBL")
public class RelateEmpTbl {

    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @Column(name = "RELATE_NO", length = 2, nullable = false)
    private String relateNo;

    @Column(name = "RELATE_EMP_ID", length = 10)
    private String relateEmpId;

    public RelateEmpTbl() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getRelateNo() {
        return relateNo;
    }

    public void setRelateNo(String relateNo) {
        this.relateNo = relateNo;
    }

    public String getRelateEmpId() {
        return relateEmpId;
    }

    public void setRelateEmpId(String relateEmpId) {
        this.relateEmpId = relateEmpId;
    }
}