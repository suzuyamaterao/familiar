package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

/**
 * 部署マスタ.
 *
 * 社員が所属する部署を管理する。
 * 部署は全部で２種類（総務、システム開発）。
 * 
 */
@Entity
@Table(name = "DEPARTMENT_MST")
public class DepartmentMst {

    @Id
    @Column(name = "DEPARTMENT_ID", length = 1, nullable = false)
    private String departmentId;

    @Column(name = "DEPARTMENT_NAME", length = 20, nullable = false)
    private String departmentName;

    public DepartmentMst() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
