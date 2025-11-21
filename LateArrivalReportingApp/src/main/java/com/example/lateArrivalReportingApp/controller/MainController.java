package com.example.lateArrivalReportingApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");

        // 未ログインならログイン画面へ
        if (username == null) {
            return "redirect:/";
        }

        model.addAttribute("username", username);

        return "main"; // main.html
    }
}
