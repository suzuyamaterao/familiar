package com.example.lateArrivalReportingApp.service;

import org.springframework.stereotype.Service;

import com.example.lateArrivalReportingApp.model.TrainMst;
import com.example.lateArrivalReportingApp.model.LateTbl;
import com.example.lateArrivalReportingApp.model.ArriveTbl;
import com.example.lateArrivalReportingApp.repository.TrainMstRepository;
import com.example.lateArrivalReportingApp.repository.LateTblRepository;
import com.example.lateArrivalReportingApp.repository.ArriveTblRepository;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ArrivalReportService {
    private final TrainMstRepository trainMstRepository;
    private final LateTblRepository lateTblRepository;
    private final ArriveTblRepository arriveTblRepository;

    public ArrivalReportService(TrainMstRepository trainMstRepository, LateTblRepository lateTblRepository,
            ArriveTblRepository arriveTblRepository) {
        this.trainMstRepository = trainMstRepository;
        this.lateTblRepository = lateTblRepository;
        this.arriveTblRepository = arriveTblRepository;
    }

    public static record TrainInfo(String label, String hhmm) {
    }

    /**
     * empId と contactDate(yyyyMMdd) で LateTbl を探し、TRAIN_ID があれば
     * TrainMst の RAILWAY 名＋遅延分（分）をラベルとして返す。
     * 遅延分は hh:mm 形式も返す（time input の value 用）。
     * 見つからない／TRAIN_ID が null の場合は null を返す。
     */
    public TrainInfo getTrainInfo(String empId, String contactDate) {
        Optional<LateTbl> lateOpt = lateTblRepository.findByEmpIdAndContactDate(empId, contactDate);

        if (lateOpt.isEmpty())
            return null;
        LateTbl late = lateOpt.get();
        String trainId = late.getTrainId();
        if (trainId == null || trainId.isBlank())
            return new TrainInfo(null, null);

        Optional<TrainMst> tmOpt = trainMstRepository.findByTrainId(trainId);
        if (tmOpt.isEmpty())
            return null;
        String railway = tmOpt.get().getRailway();

        String label = railway;
        return new TrainInfo(label, null);
    }

    /**
     * 到着報告を受け取り ArriveTbl に保存する。
     * フォームの time は "HH:mm"、DB は "HHmm" / delay は分を文字列で保存。
     */
    public void saveArrivalReport(String empId, String onTime, String Delay, String arrivalTime,
            String lateTime, String reason) {
        if (empId == null || empId.isBlank()) {
            empId = "0000000000";
        }

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HHmm");

        String contactDate = LocalDate.now().format(dateFmt);
        String contactTime = LocalTime.now().format(timeFmt);

        ArriveTbl a = new ArriveTbl();
        a.setEmpId(empId);
        a.setContactDate(contactDate);
        a.setContactTime(contactTime);

        a.setLateUmu(onTime != null ? "0" : "1");

        if (arrivalTime != null && !arrivalTime.isBlank()) {
            a.setArriveTime(arrivalTime.replace(":", ""));
        }
        if (lateTime != null && !lateTime.isBlank()) {
            a.setLateTime(lateTime.replace(":", ""));
        }

        if (Delay != null && !Delay.isBlank()) {
            try {
                LocalTime t = LocalTime.parse(Delay);
                int minutes = t.getHour() * 60 + t.getMinute();
                a.setDelay1(String.valueOf(minutes));
            } catch (Exception e) {
                a.setDelay1(null);
            }
        }

        // 固定値または LateTbl から取得する等必要に応じて変更
        a.setTrainId1("001");

        a.setInformation(reason);

        arriveTblRepository.save(a);
    }
}
