package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * ユーザロールマスタ (USER_ROLES_MST).
 * 
 * 社員の閲覧権限を管理する。
 * 
 */
@Entity
@Table(name = "USER_ROLES_MST")
public class UserRolesMst {

    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @Column(name = "ROLES_ID", length = 20, nullable = false)
    private String rolesId;

    public UserRolesMst() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }
}