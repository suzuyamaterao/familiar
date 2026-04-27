package com.example.lateArrivalReportingApp.controller;

import com.example.lateArrivalReportingApp.model.CodeMst;
import com.example.lateArrivalReportingApp.model.TeamMst;
import com.example.lateArrivalReportingApp.model.ViewEmployee;
import com.example.lateArrivalReportingApp.model.ViewLata;
import com.example.lateArrivalReportingApp.repository.CodeMstRepository;
import com.example.lateArrivalReportingApp.repository.TeamMstRepository;
import com.example.lateArrivalReportingApp.repository.ViewEmployeeRepository;
import com.example.lateArrivalReportingApp.repository.ViewLataRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HistoryController {

    private final CodeMstRepository codeMstRepository;
    private final TeamMstRepository teamMstRepository;
    private final ViewEmployeeRepository viewEmployeeRepository;
    private final ViewLataRepository viewLataRepository;

    public HistoryController(CodeMstRepository codeMstRepository, TeamMstRepository teamMstRepository,
            ViewEmployeeRepository viewEmployeeRepository, ViewLataRepository viewLataRepository) {
        this.codeMstRepository = codeMstRepository;
        this.teamMstRepository = teamMstRepository;
        this.viewEmployeeRepository = viewEmployeeRepository;
        this.viewLataRepository = viewLataRepository;
    }

    @GetMapping("/history")
    public String showSearchPage(Model model, HttpSession session) {
        // セッションから社員IDを取得
        String empId = (String) session.getAttribute("empId");

        // ViewEmployee から rolesId を取得
        List<ViewEmployee> viewEmployees = viewEmployeeRepository.findByEmpId(empId);
        String rolesId = null;
        String unitNo = null;
        String teamId = null;
        String empLname = null;
        String empFname = null;
        if (!viewEmployees.isEmpty()) {
            ViewEmployee viewEmployee = viewEmployees.get(0);
            rolesId = viewEmployee.getRolesId();
            unitNo = viewEmployee.getUnitNo();
            teamId = viewEmployee.getTeamId();
            empLname = viewEmployee.getEmpLname();
            empFname = viewEmployee.getEmpFname();
        }
        model.addAttribute("rolesId", rolesId);
        model.addAttribute("empLname", empLname);
        model.addAttribute("empFname", empFname);

        // ===== ロール制御 =====
        if ("2".equals(rolesId)) {
            model.addAttribute("currentUnitNo", unitNo);
        }

        if ("3".equals(rolesId)) {
            model.addAttribute("currentUnitNo", unitNo);
            model.addAttribute("currentTeamId", teamId);
        }

        if ("4".equals(rolesId)) {
            model.addAttribute("currentUnitNo", unitNo);
            model.addAttribute("currentTeamId", teamId);
            model.addAttribute("currentEmpId", empId);
        }

        // ユニット
        List<CodeMst> units = codeMstRepository.findByGroupId("UNIT");
        model.addAttribute("units", units);

        // チーム
        List<TeamMst> teams = teamMstRepository.findAll();
        model.addAttribute("teams", teams);

        // 社員名
        List<ViewEmployee> employees = viewEmployeeRepository.findAll();
        model.addAttribute("employees", employees);

        return "history"; // ここにユニットとチーム両方の <select> がある
    }

    /**
     * ユニット番号に紐づくチーム一覧を取得するREST API
     */
    @GetMapping("/api/teams-by-unit")
    @ResponseBody
    public Map<String, Object> getTeamsByUnit(@RequestParam("unitNo") String unitNo) {
        List<TeamMst> teams = teamMstRepository.findByUnitNo(unitNo);
        Map<String, Object> response = new HashMap<>();
        response.put("teams", teams);
        return response;
    }

    /**
     * チームIDに紐づく社員一覧を取得するREST API
     * ViewEmployeeを使用して、ユニット・チーム・部門情報を含む完全な社員情報を返す
     */
    @GetMapping("/api/employees-by-team")
    @ResponseBody
    public Map<String, Object> getEmployeesByTeam(@RequestParam("teamId") String teamId) {
        List<ViewEmployee> employees = viewEmployeeRepository.findByTeamId(teamId);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employees);
        return response;
    }

    /**
     * ユニット番号に紐づく社員一覧を取得するREST API
     * ViewEmployeeを使用して、ユニット・チーム・部門情報を含む完全な社員情報を返す
     */
    @GetMapping("/api/employees-by-unit")
    @ResponseBody
    public Map<String, Object> getEmployeesByUnit(@RequestParam("unitNo") String unitNo) {
        List<ViewEmployee> employees = viewEmployeeRepository.findByUnitNo(unitNo);
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employees);
        return response;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String units,
            @RequestParam(required = false) String teams,
            @RequestParam(required = false) String name,
            Model model,
            HttpSession session) {

        String empId = (String) session.getAttribute("empId");

        // ===== 空文字 → null =====
        units = emptyToNull(units);
        teams = emptyToNull(teams);
        name = emptyToNull(name);
        startDate = emptyToNull(startDate);
        endDate = emptyToNull(endDate);

        // ===== 日付変換 yyyy-MM-dd → yyyyMMdd =====
        startDate = formatDate(startDate);
        endDate = formatDate(endDate);

        // ===== ユーザ取得 =====
        ViewEmployee user = viewEmployeeRepository.findByEmpId(empId).get(0);
        String role = user.getRolesId();

        // ===== ロール制御 =====
        if ("2".equals(role)) {
            units = user.getUnitNo();
            model.addAttribute("currentUnitNo", user.getUnitNo());

        }

        if ("3".equals(role)) {
            units = user.getUnitNo();
            teams = user.getTeamId();
            model.addAttribute("currentUnitNo", user.getUnitNo());
            model.addAttribute("currentTeamId", user.getTeamId());
        }

        if ("4".equals(role)) {
            units = user.getUnitNo();
            teams = user.getTeamId();
            name = user.getEmpId();
            model.addAttribute("currentUnitNo", user.getUnitNo());
            model.addAttribute("currentTeamId", user.getTeamId());
            model.addAttribute("currentEmpId", user.getEmpId());
        }

        // ===== 検索 =====
        List<ViewLata> results = viewLataRepository.search(units, teams, name, startDate, endDate);

        model.addAttribute("results", results);

        // ===== 画面再表示用 =====
        model.addAttribute("rolesId", role);

        model.addAttribute("units", codeMstRepository.findByGroupId("UNIT"));
        model.addAttribute("teams", teamMstRepository.findAll());
        model.addAttribute("employees", viewEmployeeRepository.findAll());

        // // ★ 表示用は元フォーマットに戻す（yyyy-MM-dd）
        // model.addAttribute("startDate", restoreDate(startDate));
        // model.addAttribute("endDate", restoreDate(endDate));

        // model.addAttribute("selectedUnit", units);
        // model.addAttribute("selectedTeam", teams);
        // model.addAttribute("selectedName", name);

        model.addAttribute("empLname", user.getEmpLname());
        model.addAttribute("empFname", user.getEmpFname());

        return "history";
    }

    // 空文字 → null
    private String emptyToNull(String val) {
        return (val == null || val.isEmpty()) ? null : val;
    }

    // yyyy-MM-dd → yyyyMMdd
    private String formatDate(String date) {
        if (date == null)
            return null;
        return date.replace("-", "");
    }

    // yyyyMMdd → yyyy-MM-dd（画面表示用）
    private String restoreDate(String date) {
        if (date == null || date.length() != 8)
            return date;
        return date.substring(0, 4) + "-" +
                date.substring(4, 6) + "-" +
                date.substring(6, 8);
    }

    @GetMapping("/export-csv")
    @ResponseBody
    public void exportCsv(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String units,
            @RequestParam(required = false) String teams,
            @RequestParam(required = false) String name,
            HttpSession session,
            HttpServletResponse response) throws IOException {

        String empId = (String) session.getAttribute("empId");

        // ===== 空文字 → null =====
        units = emptyToNull(units);
        teams = emptyToNull(teams);
        name = emptyToNull(name);
        startDate = emptyToNull(startDate);
        endDate = emptyToNull(endDate);

        // ===== 日付変換 =====
        startDate = formatDate(startDate);
        endDate = formatDate(endDate);

        // ===== ロール制御 =====
        ViewEmployee user = viewEmployeeRepository.findByEmpId(empId).get(0);
        String role = user.getRolesId();

        if ("2".equals(role)) {
            units = user.getUnitNo();
        }
        if ("3".equals(role)) {
            units = user.getUnitNo();
            teams = user.getTeamId();
        }
        if ("4".equals(role)) {
            units = user.getUnitNo();
            teams = user.getTeamId();
            name = user.getEmpId();
        }

        // ===== データ取得 =====
        List<ViewLata> results = viewLataRepository.search(units, teams, name, startDate, endDate);

        // ===== レスポンス設定 =====
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=history.csv");

        PrintWriter writer = response.getWriter();

        writer.write('\uFEFF');
        // ===== ヘッダー =====
        writer.println(String.join(",",
                "社員ID",
                "日付",
                "ユニットNo",
                "ユニット名",
                "チームID",
                "チーム名",
                "名前",
                "理由コード",
                "理由名",
                "遅刻連絡時間",
                "電車ID",
                "路線",
                "到着予定時刻",
                "遅刻連絡内容",
                "遅刻有無",
                "遅刻有無名",
                "到着連絡時間",
                "遅刻時間",
                "到着時間",
                "遅延分",
                "到着連絡内容"));

        // ===== データ =====
        for (ViewLata item : results) {
            writer.println(String.join(",",
                    safe(item.getEmpId()),
                    safe(item.getContactDate()),
                    safe(item.getUnitNo()),
                    safe(item.getUnitNm()),
                    safe(item.getTeamId()),
                    safe(item.getTeamName()),
                    safe(item.getName()),
                    safe(item.getReason()),
                    safe(item.getReasonNm()),
                    safe(item.getLateContactTime()),
                    safe(item.getTrainId()),
                    safe(item.getRailway()),
                    safe(item.getEta()),
                    safe(item.getLateInformation()),
                    safe(item.getLateUmu()),
                    safe(item.getLateUmuNm()),
                    safe(item.getArrContactTime()),
                    safe(item.getLateTime()),
                    safe(item.getArriveTime()),
                    safe(item.getDelay()),
                    safe(item.getArrInformation())));
        }

        writer.flush();
    }

    private String safe(String val) {
        if (val == null)
            return "";

        // カンマ・改行対策
        if (val.contains(",") || val.contains("\"") || val.contains("\n")) {
            val = val.replace("\"", "\"\"");
            return "\"" + val + "\"";
        }
        return val;
    }

}