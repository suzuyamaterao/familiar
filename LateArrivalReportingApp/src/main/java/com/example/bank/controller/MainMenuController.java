package com.example.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainMenuController {
    @GetMapping("/main_menu")
    public String display() {
        return "main_menu";
    }

    @PostMapping("/history")
    public String history() {
            return "history";
    }

    @PostMapping("/transfer")
    public String transfer() {
            return "transfer";
    }
}
