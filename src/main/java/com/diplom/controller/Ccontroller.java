package com.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class Ccontroller {

    @GetMapping("/menu")
    public String getMenu(Model model){
        model.addAttribute("menu");
        return "menu";
    }

    @GetMapping("/profile")
    public String getCustomerProfile(Model model){
        model.addAttribute("profile");
        return "profile";
    }
}
