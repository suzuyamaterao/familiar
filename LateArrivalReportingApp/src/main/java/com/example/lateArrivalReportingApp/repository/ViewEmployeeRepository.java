package com.example.lateArrivalReportingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lateArrivalReportingApp.model.ViewEmployee;
import java.util.List;

public interface ViewEmployeeRepository extends JpaRepository<ViewEmployee, String> {

    List<ViewEmployee> findByUnitNo(String unitNo);

    List<ViewEmployee> findByTeamId(String teamId);

    List<ViewEmployee> findByDepartmentId(String departmentId);

    List<ViewEmployee> findByEmpId(String empId);
}