package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.UserRolesMst;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRolesMstRepository extends JpaRepository<UserRolesMst, String> {

    List<UserRolesMst> findByRolesId(String rolesId);

}
