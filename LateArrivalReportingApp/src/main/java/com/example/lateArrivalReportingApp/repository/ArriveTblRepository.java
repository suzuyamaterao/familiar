package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.ArriveTbl;
import com.example.lateArrivalReportingApp.model.ArriveTblId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArriveTblRepository extends JpaRepository<ArriveTbl, ArriveTblId> {

    boolean existsByEmpIdAndContactDate(String empId, String contactDate);

}
