package com.example.lateArrivalReportingApp.controller;

import com.example.lateArrivalReportingApp.model.CodeMst;
import com.example.lateArrivalReportingApp.model.EmployeeMst;
import com.example.lateArrivalReportingApp.model.TeamMst;
import com.example.lateArrivalReportingApp.model.UserRolesMst;
import com.example.lateArrivalReportingApp.repository.CodeMstRepository;
import com.example.lateArrivalReportingApp.repository.EmployeeMstRepository;
import com.example.lateArrivalReportingApp.repository.TeamMstRepository;
import com.example.lateArrivalReportingApp.repository.UserRolesMstRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {

    private final CodeMstRepository codeMstRepository;
    private final TeamMstRepository teamMstRepository;
    private final EmployeeMstRepository employeeMstRepository;
    private final UserRolesMstRepository userRolesMstRepository;

    public HistoryController(CodeMstRepository codeMstRepository, TeamMstRepository teamMstRepository,
            EmployeeMstRepository employeeMstRepository, UserRolesMstRepository userRolesMstRepository) {
        this.codeMstRepository = codeMstRepository;
        this.teamMstRepository = teamMstRepository;
        this.employeeMstRepository = employeeMstRepository;
        this.userRolesMstRepository = userRolesMstRepository;

    }

    @GetMapping("/history")
    public String showSearchPage(Model model) {

        // 社員ID取得
        String empId = (String) model.getAttribute("empId");
        // チームID取得
        String empTermId = (String) model.getAttribute("empTermId");

        // ユーザーロール取得
        List<UserRolesMst> userRoles = userRolesMstRepository.findByRolesId(empId);
        // model.addAttribute("userRoles", userRoles);

        // 社員IDに紐づくロールIDが4(MEMBER)だった場合
        if (userRoles.stream().anyMatch(r -> r.getRolesId().equals("MEMBER"))) {
            // チームIDに紐づくチームマスタ情報取得
            List<TeamMst> teams = teamMstRepository.findByTeamId(empTermId);
            model.addAttribute("teams", teams);

            // model.addAttribute("isAdmin", true);
            // } else {
            // model.addAttribute("isAdmin", false);
        }

        // ユニット
        List<CodeMst> units = codeMstRepository.findByGroupId("UNIT");
        model.addAttribute("units", units);

        // 社員名
        List<EmployeeMst> employees = employeeMstRepository.findAll();
        model.addAttribute("employees", employees);

        return "history"; // ここにユニットとチーム両方の <select> がある
    }
}