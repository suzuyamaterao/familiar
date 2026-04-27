package com.example.lateArrivalReportingApp.model;

import java.io.Serializable;
import java.util.Objects;

public class ViewLataId implements Serializable {

    private String empId;
    private String contactDate;

    public ViewLataId() {
    }

    public ViewLataId(String empId, String contactDate) {
        this.empId = empId;
        this.contactDate = contactDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ViewLataId))
            return false;
        ViewLataId that = (ViewLataId) o;
        return Objects.equals(empId, that.empId) &&
                Objects.equals(contactDate, that.contactDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, contactDate);
    }
}