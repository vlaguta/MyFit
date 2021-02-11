package com.diplom.controller;

import com.diplom.service.DailyMenuService;
import com.diplom.service.PhotoService;
import com.diplom.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class Ccontroller {

    private final DailyMenuService dailyMenuService;
    private final PostService postService;
    private final PhotoService photoService;

    @GetMapping("/menu")
    public String getMenu(Model model){
        model.addAttribute("menu");
        return "menu";
    }

    @GetMapping("/profile")
    public String getCustomerProfile(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        //model.addAttribute("dailyMenu", dailyMenuService.getDailyMenu())
        return "profile";
    }
}
