package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.ViewLata;
import com.example.lateArrivalReportingApp.model.ViewLataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewLataRepository extends JpaRepository<ViewLata, ViewLataId> {

    @Query("""
                SELECT v FROM ViewLata v
                WHERE (:unitNo IS NULL OR v.unitNo = :unitNo)
                  AND (:teamId IS NULL OR v.teamId = :teamId)
                  AND (:empId IS NULL OR v.empId = :empId)
                  AND (:fromDate IS NULL OR v.contactDate >= :fromDate)
                  AND (:toDate IS NULL OR v.contactDate <= :toDate)
            """)
    List<ViewLata> search(
            @Param("unitNo") String unitNo,
            @Param("teamId") String teamId,
            @Param("empId") String empId,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate);
}