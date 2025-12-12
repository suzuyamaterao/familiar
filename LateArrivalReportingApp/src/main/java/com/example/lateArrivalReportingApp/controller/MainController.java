package com.example.lateArrivalReportingApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.lateArrivalReportingApp.repository.EmployeeMstRepository;
import com.example.lateArrivalReportingApp.repository.TrainMstRepository;
import com.example.lateArrivalReportingApp.model.EmployeeMst;
import com.example.lateArrivalReportingApp.model.TrainMst;

import java.util.Optional;
import com.example.lateArrivalReportingApp.repository.CodeMstRepository;
import com.example.lateArrivalReportingApp.model.CodeMst;
import java.util.List;

@Controller
public class MainController {

    private final EmployeeMstRepository employeeMstRepository;
    private final CodeMstRepository codeMstRepository;
    private final TrainMstRepository trainMstRepository;

    // コンストラクタを1つに統一（両リポジトリを注入）
    public MainController(EmployeeMstRepository employeeMstRepository,
            CodeMstRepository codeMstRepository,
            TrainMstRepository trainMstRepository) {
        this.employeeMstRepository = employeeMstRepository;
        this.codeMstRepository = codeMstRepository;
        this.trainMstRepository = trainMstRepository;
    }

    @GetMapping("/late-arrival-report")
    public String showArrivalReport(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null) {
            return "redirect:/";
        }

        model.addAttribute("empId", empId);

        // 社員名を model に追加
        employeeMstRepository.findById(empId).ifPresent(e -> {
            model.addAttribute("empLname", e.getEmpLname());
            model.addAttribute("empFname", e.getEmpFname());
        });

        // CodeMst から遅刻理由（groupId = LATE_REASON）を取得して model に渡す
        List<CodeMst> delayReasons = codeMstRepository.findByGroupId("LATE_REASON");
        model.addAttribute("delayReasons", delayReasons);

        // TrainMst から会社/路線リストを取得して渡す
        List<TrainMst> trains = trainMstRepository.findAllByOrderByTrainIdAsc();
        model.addAttribute("trains", trains);

        return "late-arrival-report";
    }

    // 互換性のための alias
    @GetMapping("/report")
    public String showReportAlias(HttpSession session, Model model) {
        return showArrivalReport(session, model);
    }

    // 既存のリンク (/arrival-report) に対応するエイリアス
    @GetMapping("/arrival-report")
    public String showLateArrivalReportAlias(HttpSession session, Model model) {
        return showArrivalReport(session, model);
    }

    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null) {
            return "redirect:/";
        }

        model.addAttribute("empId", empId);

        Optional<EmployeeMst> empOpt = employeeMstRepository.findById(empId);
        if (empOpt.isPresent()) {
            EmployeeMst emp = empOpt.get();
            model.addAttribute("empLname", emp.getEmpLname());
            model.addAttribute("empFname", emp.getEmpFname());
        } else {
            model.addAttribute("empLname", "");
            model.addAttribute("empFname", "");
        }

        return "main";
    }

    @GetMapping("/history")
    public String showInquiry(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null)
            return "redirect:/";
        model.addAttribute("empId", empId);
        employeeMstRepository.findById(empId).ifPresent(e -> {
            model.addAttribute("empLname", e.getEmpLname());
            model.addAttribute("empFname", e.getEmpFname());
        });
        return "history";
    }

    @GetMapping("/route-setting")
    public String showRouteSetting(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null)
            return "redirect:/";
        model.addAttribute("empId", empId);
        employeeMstRepository.findById(empId).ifPresent(e -> {
            model.addAttribute("empLname", e.getEmpLname());
            model.addAttribute("empFname", e.getEmpFname());
        });
        return "route-setting";
    }

    @GetMapping("/user-register")
    public String showSiteEmployeeRegistration(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null)
            return "redirect:/";
        model.addAttribute("empId", empId);
        employeeMstRepository.findById(empId).ifPresent(e -> {
            model.addAttribute("empLname", e.getEmpLname());
            model.addAttribute("empFname", e.getEmpFname());
        });
        return "user-register";
    }
}
