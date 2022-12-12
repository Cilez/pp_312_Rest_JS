//package com.mygroup.kata.controller;
//
//import com.mygroup.kata.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.security.Principal;
//
//@Controller
//public class UserController {
//    private UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
////    @GetMapping(value = "/oneUser")
////    public String getUserPage(ModelMap modelMap, Principal principal) {
////        var a = userService.findUserByName(principal.getName());
////        modelMap.addAttribute("user", a);
////        return "OLDoneUser";
////    }
//
////    @GetMapping("oneUser")
////    public String test() {
////        return "testPage";
////    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String redirect() {
////        return "redirect:/login";
//        return "/login";
//    } // делает пыр-пыр, при заходе на сервер
//}