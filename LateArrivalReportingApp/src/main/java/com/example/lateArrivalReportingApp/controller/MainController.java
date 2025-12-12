package com.example.lateArrivalReportingApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.lateArrivalReportingApp.repository.EmployeeMstRepository;
import com.example.lateArrivalReportingApp.model.EmployeeMst;
import java.util.Optional;

@Controller
public class MainController {

    private final EmployeeMstRepository employeeMstRepository;

    public MainController(EmployeeMstRepository employeeMstRepository) {
        this.employeeMstRepository = employeeMstRepository;
    }

    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {

        String empId = (String) session.getAttribute("empId");

        // 未ログインならログイン画面へ
        if (empId == null) {
            return "redirect:/";
        }

        model.addAttribute("empId", empId);

        // EmployeeMst から氏名を取得して model に追加
        Optional<EmployeeMst> empOpt = employeeMstRepository.findById(empId);
        if (empOpt.isPresent()) {
            EmployeeMst emp = empOpt.get();
            model.addAttribute("empLname", emp.getEmpLname());
            model.addAttribute("empFname", emp.getEmpFname());
        } else {
            model.addAttribute("empLname", "");
            model.addAttribute("empFname", "");
        }

        return "main"; // main.html
    }

    // --- ここから追加: 各ボタンの遷移先ハンドラ ---
    @GetMapping("/late-arrival-report")
    public String showReport(HttpSession session, Model model) {
        String empId = (String) session.getAttribute("empId");
        if (empId == null)
            return "redirect:/";
        model.addAttribute("empId", empId);
        employeeMstRepository.findById(empId).ifPresent(e -> {
            model.addAttribute("empLname", e.getEmpLname());
            model.addAttribute("empFname", e.getEmpFname());
        });
        return "late-arrival-report"; // templates/report.html を作成
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
        return "history"; // templates/inquiry.html を作成
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
        return "route-setting"; // templates/routeSetting.html を作成
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
        return "user-register"; // templates/siteEmployeeRegistration.html を作成
    }
}
