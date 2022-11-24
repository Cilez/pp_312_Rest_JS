//package com.mygroup.kata.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class HelloController {
//
//    @GetMapping()
//    public String printWelcome(ModelMap model) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Message from HelloController");
//        model.addAttribute("messages", messages);
//        return "index";
//    }
//}