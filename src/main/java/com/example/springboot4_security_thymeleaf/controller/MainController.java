package com.example.springboot4_security_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}

