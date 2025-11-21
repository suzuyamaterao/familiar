package com.example.lateArrivalReportingApp.controller;

import com.example.lateArrivalReportingApp.service.LoginService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    // ログイン画面表示
    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // login.html
    }

    // ログイン処理
    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        boolean success = loginService.authenticate(username, password);

        if (success) {
            session.setAttribute("username", username);
            return "redirect:/main"; // メニュー画面へ
        } else {
            model.addAttribute("error", "ユーザー名またはパスワードが違います");
            return "login";
        }
    }
}