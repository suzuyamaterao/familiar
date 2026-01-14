package com.example.lateArrivalReportingApp.controller;

import com.example.lateArrivalReportingApp.repository.ArriveTblRepository;
import com.example.lateArrivalReportingApp.service.ArrivalReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ArrivalReportController {

    private final ArrivalReportService arrivalReportService;

    public ArrivalReportController(ArriveTblRepository arriveTblRepository, ArrivalReportService arrivalReportService) {
        this.arrivalReportService = arrivalReportService;
    }

    @GetMapping("/submit-train-delay")
    public String showArrivalForm(Model model, @RequestParam(value = "empId", required = false) String empId) {
        if (empId == null || empId.isBlank()) {
            empId = "0000000000"; // 実運用ではセッション等から取得
        }
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String contactDate = LocalDate.now().format(dateFmt);

        var info = arrivalReportService.getTrainInfo(empId, contactDate);
        if (info != null) {
            model.addAttribute("DelayLabel", info.label());
            model.addAttribute("DelayTime", info.hhmm());
        }
        return "arrival-report";
    }

    @PostMapping("/submit-train-delay")
    public String submitTrainDelay(
            @RequestParam(value = "empId", required = false) String empId,
            @RequestParam(value = "on_time", required = false) String onTime,
            @RequestParam(value = "train_delay_time", required = false) String Delay,
            @RequestParam(value = "arrival_time", required = false) String arrivalTime,
            @RequestParam(value = "late_time", required = false) String lateTime,
            @RequestParam(value = "reason", required = false) String reason) {

        arrivalReportService.saveArrivalReport(empId, onTime, Delay, arrivalTime, lateTime, reason);
        return "redirect:/?saved";
    }
}
