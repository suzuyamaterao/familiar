package com.example.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    @GetMapping("/history")
    public String showHistoryPage() {
        return "history";
    }
}
