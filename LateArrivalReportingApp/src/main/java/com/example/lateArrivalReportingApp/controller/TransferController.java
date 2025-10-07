package com.example.lateArrivalReportingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferController {

    @GetMapping("/transfer")
    public String display() {
        return "transfer";
    }

    @PostMapping("/transferConfirmation")
    public String transferConfirmation() {
            return "transferConfirmation";
    }
}
