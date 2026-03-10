package com.example.lateArrivalReportingApp.controller;

import com.example.lateArrivalReportingApp.model.CodeMst;
import com.example.lateArrivalReportingApp.model.EmployeeMst;
import com.example.lateArrivalReportingApp.model.TeamMst;
import com.example.lateArrivalReportingApp.repository.CodeMstRepository;
import com.example.lateArrivalReportingApp.repository.EmployeeMstRepository;
import com.example.lateArrivalReportingApp.repository.TeamMstRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {

    private final CodeMstRepository codeMstRepository;
    private final TeamMstRepository teamMstRepository;
    private final EmployeeMstRepository employeeMstRepository;

    public HistoryController(CodeMstRepository codeMstRepository, TeamMstRepository teamMstRepository,
            EmployeeMstRepository employeeMstRepository) {
        this.codeMstRepository = codeMstRepository;
        this.teamMstRepository = teamMstRepository;
        this.employeeMstRepository = employeeMstRepository;
    }

    @GetMapping("/employee/search")
    public String showSearchPage(Model model) {
        // ユニット
        List<CodeMst> units = codeMstRepository.findByGroupId("UNIT");
        model.addAttribute("units", units);

        // チーム
        List<TeamMst> teams = teamMstRepository.findAll();
        model.addAttribute("teams", teams);

        // 社員名
        List<EmployeeMst> employees = employeeMstRepository.findAll();
        model.addAttribute("employees", employees);

        return "employeeSearch"; // ここにユニットとチーム両方の <select> がある
    }
}