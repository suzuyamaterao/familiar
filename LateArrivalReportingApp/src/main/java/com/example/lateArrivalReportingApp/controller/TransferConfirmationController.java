package com.example.lateArrivalReportingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferConfirmationController {

    @GetMapping("/transferConfirmation")
    public String display() {
        return "transferConfirmation";
    }
    
}
