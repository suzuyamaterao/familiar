package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LateTblId implements Serializable {

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "CONTACT_DATE")
    private String contactDate;

    public LateTblId() {
    }

    public LateTblId(String empId, String contactDate) {
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
        if (!(o instanceof LateTblId))
            return false;
        LateTblId that = (LateTblId) o;
        return Objects.equals(empId, that.empId) &&
                Objects.equals(contactDate, that.contactDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, contactDate);
    }
}