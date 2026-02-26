package com.example.springboot4_security_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String get(Model model) {

        return "index";
    }

    @GetMapping("/second")
    public String secondPage() {
        return "second";
    }
}

