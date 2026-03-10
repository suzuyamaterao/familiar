package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.TeamMst;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMstRepository extends JpaRepository<TeamMst, String> {

        List<TeamMst> findByTeamId(String teamId);

}
