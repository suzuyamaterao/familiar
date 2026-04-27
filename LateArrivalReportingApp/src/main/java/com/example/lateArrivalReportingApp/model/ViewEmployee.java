package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "view_employee")
@Immutable // 更新不可（重要）
public class ViewEmployee {

    @Id
    @Column(name = "emp_id")
    private String empId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "unit_no")
    private String unitNo;

    @Column(name = "unit_nm")
    private String unitNm;

    @Column(name = "role")
    private String role;

    @Column(name = "role_nm")
    private String roleNm;

    @Column(name = "emp_lname")
    private String empLname;

    @Column(name = "emp_fname")
    private String empFname;

    @Column(name = "emp_lname_kana")
    private String empLnameKana;

    @Column(name = "emp_fname_kana")
    private String empFnameKana;

    @Column(name = "gender")
    private String gender;

    @Column(name = "gender_nm")
    private String genderNm;

    @Column(name = "belong")
    private String belong;

    @Column(name = "belong_nm")
    private String belongNm;

    @Column(name = "emp_status")
    private String empStatus;

    @Column(name = "emp_status_nm")
    private String empStatusNm;

    @Column(name = "roles_id")
    private String rolesId;

    public String getEmpId() {
        return empId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public String getUnitNm() {
        return unitNm;
    }

    public String getRole() {
        return role;
    }

    public String getRoleNm() {
        return roleNm;
    }

    public String getEmpLname() {
        return empLname;
    }

    public String getEmpFname() {
        return empFname;
    }

    public String getEmpLnameKana() {
        return empLnameKana;
    }

    public String getEmpFnameKana() {
        return empFnameKana;
    }

    public String getGender() {
        return gender;
    }

    public String getGenderNm() {
        return genderNm;
    }

    public String getBelong() {
        return belong;
    }

    public String getBelongNm() {
        return belongNm;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public String getEmpStatusNm() {
        return empStatusNm;
    }

    public String getRolesId() {
        return rolesId;
    }
}