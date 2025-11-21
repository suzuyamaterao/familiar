package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * 社員マスタ.
 *
 * 社員の基本情報を管理する。
 * 
 */
@Entity
@Table(name = "EMPLOYEE_MST")
public class EmployeeMst {

    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false, unique = true)
    private String empId;

    @Column(name = "DEPARTMENT_ID", length = 1)
    private String departmentId;

    @Column(name = "TEAM_ID", length = 5)
    private String teamId;

    @Column(name = "EMP_NO", length = 4)
    private String empNo;

    @Column(name = "ROLE", length = 1, columnDefinition = "char(1) default '0'", nullable = false)
    private String role = "0";

    @Column(name = "EMP_LNAME", length = 10, nullable = false)
    private String empLname;

    @Column(name = "EMP_FNAME", length = 10, nullable = false)
    private String empFname;

    @Column(name = "EMP_LNAME_KANA", length = 20, nullable = false)
    private String empLnameKana;

    @Column(name = "EMP_FNAME_KANA", length = 20, nullable = false)
    private String empFnameKana;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "BELONG", length = 1, nullable = false)
    private String belong;

    @Column(name = "EMP_STATUS", length = 1, columnDefinition = "char(1) default '0'", nullable = false)
    private String empStatus = "0";

    @Column(name = "CHANGE_DATE")
    private LocalDate changeDate;

    @Column(name = "MAIL_ADDRESS", length = 100)
    private String mailAddress;

    public EmployeeMst() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmpLname() {
        return empLname;
    }

    public void setEmpLname(String empLname) {
        this.empLname = empLname;
    }

    public String getEmpFname() {
        return empFname;
    }

    public void setEmpFname(String empFname) {
        this.empFname = empFname;
    }

    public String getEmpLnameKana() {
        return empLnameKana;
    }

    public void setEmpLnameKana(String empLnameKana) {
        this.empLnameKana = empLnameKana;
    }

    public String getEmpFnameKana() {
        return empFnameKana;
    }

    public void setEmpFnameKana(String empFnameKana) {
        this.empFnameKana = empFnameKana;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}