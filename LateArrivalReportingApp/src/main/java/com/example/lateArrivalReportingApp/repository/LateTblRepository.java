package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.LateTbl;
import com.example.lateArrivalReportingApp.model.LateTblId;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LateTblRepository extends JpaRepository<LateTbl, LateTblId> {
    // 当日分の報告が既に存在するか確認するメソッド
    boolean existsByIdEmpIdAndIdContactDate(String empId, String contactDate);

    // 当日分の報告データを取得する。
    Optional<LateTbl> findByIdEmpIdAndIdContactDate(String empId, String contactDate);
}