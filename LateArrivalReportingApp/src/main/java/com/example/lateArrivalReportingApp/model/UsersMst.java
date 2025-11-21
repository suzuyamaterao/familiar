package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

/**
 * ユーザテーブル.
 *
 * 社員の閲覧権限を管理する。
 * 
 */
@Entity
@Table(name = "USERS_MST")
public class UsersMst {

    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @Column(name = "PASSWORD", length = 50, nullable = false)
    private String password;

    // getters / setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
