package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.CodeMst;
import com.example.lateArrivalReportingApp.model.CodeMstId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CodeMstRepository extends JpaRepository<CodeMst, CodeMstId> {
    List<CodeMst> findByGroupId(String groupId);

    List<CodeMst> findByGroupIdOrderByCodeIdAsc(String groupId);
}
