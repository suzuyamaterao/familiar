package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.LateTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LateTblRepository extends JpaRepository<LateTbl, String> {
    Optional<LateTbl> findByEmpIdAndContactDate(String empId, String contactDate);
}
