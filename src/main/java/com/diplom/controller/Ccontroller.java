package com.diplom.controller;

import com.diplom.service.CustomerService;
import com.diplom.service.DailyMenuService;
import com.diplom.service.PhotoService;
import com.diplom.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class Ccontroller {

    private final DailyMenuService dailyMenuService;
    private final PostService postService;
    private final PhotoService photoService;
    private final CustomerService customerService;

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("menu");
        return "menu";
    }

    @GetMapping("/profile")
    public String getCustomerProfile(Model model, Principal principal) {
        model.addAttribute("posts", postService.getAllPosts());
        if(principal!=null){
            model.addAttribute("customer", customerService.getCustomer(principal.getName()));
        }
        //model.addAttribute("dailyMenu", dailyMenuService.getDailyMenu())
        return "profile";
    }
}
