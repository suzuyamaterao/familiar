package com.example.lateArrivalReportingApp.model;

import java.io.Serializable;
import java.util.Objects;

public class ArriveTblId implements Serializable {

    private String empId;
    private String contactDate;

    public ArriveTblId() {
    }

    public ArriveTblId(String empId, String contactDate) {
        this.empId = empId;
        this.contactDate = contactDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ArriveTblId))
            return false;
        ArriveTblId that = (ArriveTblId) o;
        return Objects.equals(empId, that.empId) &&
                Objects.equals(contactDate, that.contactDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, contactDate);
    }
}