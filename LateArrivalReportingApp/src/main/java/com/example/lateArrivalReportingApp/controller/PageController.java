package com.example.lateArrivalReportingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login"; // → templates/login.html を返す
    }

    @GetMapping("/main")
    public String main() {
        return "main"; // → templates/main.html を返す
    }

    @GetMapping("/user-register")
    public String userRegister() {
        return "user-register"; // → templates/user-register.html を返す
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/arrival-report")
    public String arrivalReport() {
        return "arrival-report";
    }

    @GetMapping("/late-arrival-report")
    public String lateArrivalReport() {
        return "late-arrival-report";
    }

    @GetMapping("/route-setting")
    public String routeSetting() {
        return "route-setting";
    }
}