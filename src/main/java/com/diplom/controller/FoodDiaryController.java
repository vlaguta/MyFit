package com.diplom.controller;

import com.diplom.service.DailyMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food-diaries")
public class FoodDiaryController {

    private final DailyMenuService dailyMenuService;

    public FoodDiaryController(DailyMenuService dailyMenuService) {
        this.dailyMenuService = dailyMenuService;
    }

    //выводит все дневные меню
    @GetMapping
    public String getAllDailyMenu(Model model){
        model.addAttribute("foodDiary", dailyMenuService.getAllDailyMenus());
        return "foodDiary/foodDiary";
    }
}

