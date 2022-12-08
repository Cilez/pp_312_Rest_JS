package com.mygroup.kata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/allUsers")
    public String allUsers() {
        return "allUsers";
    }
}
