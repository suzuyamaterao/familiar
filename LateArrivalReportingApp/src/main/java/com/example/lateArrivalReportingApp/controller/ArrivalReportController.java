package com.example.lateArrivalReportingApp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.lateArrivalReportingApp.model.LateTblId;
import com.example.lateArrivalReportingApp.repository.LateTblRepository;
import com.example.lateArrivalReportingApp.service.ArrivalReportService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ArrivalReportController {

    private final ArrivalReportService arrivalReportService;
    private final LateTblRepository lateTblRepository;

    public ArrivalReportController(
            ArrivalReportService arrivalReportService,
            LateTblRepository lateTblRepository) {
        this.arrivalReportService = arrivalReportService;
        this.lateTblRepository = lateTblRepository;
    }

    @GetMapping("/arrival-report")
    public String showArrivalReport(Model model, HttpSession session) {

        String empId = (String) session.getAttribute("empId");
        if (empId == null) {
            return "redirect:/login";
        }

        String contactDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 遅刻報告があるかどうかはGETでは表示しない
        boolean lateExists = lateTblRepository.existsById(new LateTblId(empId, contactDate));
        if (lateExists) {
            ArrivalReportService.TrainInfo trainInfo = arrivalReportService.getTrainInfo(empId, contactDate);
            if (trainInfo != null) {
                model.addAttribute("trainLabel", trainInfo.label());
                model.addAttribute("trainId", trainInfo.trainId());
            }
        }

        return "arrival-report";
    }

    @PostMapping("/submit-train-delay")
    public String submitArrival(
            HttpSession session,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "on_time", required = false) String onTime,
            @RequestParam(value = "train_delay_time", required = false) String delay,
            @RequestParam(value = "train_id", required = false) String trainId,
            @RequestParam(value = "arrival_time", required = false) String arrivalTime,
            @RequestParam(value = "late_time", required = false) String lateTime,
            @RequestParam(value = "reason", required = false) String reason) {

        String empId = (String) session.getAttribute("empId");
        if (empId == null) {
            return "redirect:/login";
        }

        String contactDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // =========================
        // 遅刻報告未提出チェック
        // =========================
        boolean lateExists = lateTblRepository.existsById(new LateTblId(empId, contactDate));
        if (!lateExists) {
            redirectAttributes.addFlashAttribute("existence", true);
            return "redirect:/arrival-report";
        }

        // =========================
        // 到着報告重複チェック
        // =========================
        boolean arrivalExists = arrivalReportService.existsArrivalReport(empId, contactDate);
        if (arrivalExists) {
            redirectAttributes.addFlashAttribute("already", true);
            return "redirect:/arrival-report";
        }

        // =========================
        // 保存
        // =========================
        arrivalReportService.saveArrivalReport(empId, onTime, trainId, delay, arrivalTime, lateTime, reason);

        return "redirect:/main";
    }
}