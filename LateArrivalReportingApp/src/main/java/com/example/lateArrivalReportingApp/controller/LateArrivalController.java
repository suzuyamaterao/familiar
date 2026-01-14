package com.example.lateArrivalReportingApp.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.lateArrivalReportingApp.model.LateTbl;
import com.example.lateArrivalReportingApp.repository.LateTblRepository;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class LateArrivalController {

    private static final Logger logger = LoggerFactory.getLogger(LateArrivalController.class);

    private final LateTblRepository lateTblRepository;

    public LateArrivalController(LateTblRepository lateTblRepository) {
        this.lateTblRepository = lateTblRepository;
    }

    @PostMapping("/submit-delay")
    public String submitDelay(HttpSession session,
            @RequestParam("delay_reason") String delayReason,
            @RequestParam(value = "train_id", required = false) String trainId,
            @RequestParam(value = "arrival_time", required = false) String arrivalTime,
            @RequestParam(value = "reason", required = false) String information) {

        String empId = (String) session.getAttribute("empId");
        if (empId == null) {
            logger.warn("submit-delay called without empId in session");
            return "redirect:/"; // ログインページ等へ
        }

        // 当日キー (yyyyMMdd)
        String ymd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 既に当日分の報告がある場合はエラー（2回目以降は例外投げてエラーにする）
        if (lateTblRepository.existsByEmpIdAndContactDate(empId, ymd)) {
            logger.warn("duplicate late report attempt: empId={}, date={}", empId, ymd);
            return "redirect:/late-arrival-report?duplicate=1";
        }

        try {
            LateTbl entry = new LateTbl();
            entry.setEmpId(empId);

            String nowHhmm = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            entry.setContactDate(nowHhmm);

            entry.setReason(delayReason);
            entry.setTrainId((trainId != null && !trainId.isEmpty()) ? trainId : null);

            if (arrivalTime != null && !arrivalTime.isBlank()) {
                String eta = arrivalTime.replaceAll("\\D", "");
                if (eta.length() == 3)
                    eta = String.format("%04d", Integer.parseInt(eta));
                if (eta.length() == 4)
                    entry.setEta(eta);
                else
                    entry.setEta(null);
            } else {
                entry.setEta(null);
            }

            entry.setInformation((information != null && !information.isBlank()) ? information : null);

            lateTblRepository.save(entry);
            logger.info("Saved LateTbl: empId={}, reason={}, trainId={}, eta={}", empId, delayReason, trainId,
                    entry.getEta());
            return "redirect:/main";
        } catch (Exception ex) {
            logger.error("failed to save late entry", ex);
            return "error";
        }
    }
}