package com.example.lateArrivalReportingApp.repository;

import com.example.lateArrivalReportingApp.model.TrainMst;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrainMstRepository extends JpaRepository<TrainMst, String> {
    List<TrainMst> findAllByOrderByTrainIdAsc();

    Optional<TrainMst> findByTrainId(String trainId);
}
