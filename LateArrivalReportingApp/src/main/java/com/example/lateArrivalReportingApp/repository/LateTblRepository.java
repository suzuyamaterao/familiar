package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.LateTbl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LateTblRepository extends JpaRepository<LateTbl, String> {
    // 当日分の報告が既に存在するか確認するメソッド
    boolean existsByEmpIdAndContactDate(String empId, String contactDate);
}