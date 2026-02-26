package com.example.springboot4_security_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("timestamp", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return "greeting";
    }
}

