package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.UsersMst;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersMst, String> {
    Optional<UsersMst> findByEmpId(String empId);
}