package com.example.lateArrivalReportingApp.controller;

import com.example.lateArrivalReportingApp.repository.ArriveTblRepository;
import com.example.lateArrivalReportingApp.service.ArrivalReportService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/arrival-report")
    public String showArrivalForm(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null || empId.isBlank()) {
            return "redirect:/"; // セッションに empId がなければトップへ
        }

        model.addAttribute("empId", empId);

        // 当日キー (yyyyMMdd)
        String contactDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        var info = arrivalReportService.getTrainInfo(empId, contactDate);
        if (info == null) {
            // 遅延情報がない場合はメインページへリダイレクト
            return "redirect:/arrival-report?existence=1";
        }

        model.addAttribute("DelayLabel", info.label());
        model.addAttribute("DelayTime", info.hhmm());
        return "arrival-report";
    }

    @PostMapping("/submit-train-delay")
    public String submitTrainDelay(HttpSession session,
            @RequestParam(value = "on_time", required = false) String onTime,
            @RequestParam(value = "train_delay_time", required = false) String Delay,
            @RequestParam(value = "arrival_time", required = false) String arrivalTime,
            @RequestParam(value = "late_time", required = false) String lateTime,
            @RequestParam(value = "reason", required = false) String reason) {

        String empId = (String) session.getAttribute("empId");
        if (empId == null || empId.isBlank()) {
            empId = "0000000000"; // セッションなし時のフォールバック
        }

        arrivalReportService.saveArrivalReport(empId, onTime, Delay, arrivalTime, lateTime, reason);
        return "redirect:/?saved";
    }
}
