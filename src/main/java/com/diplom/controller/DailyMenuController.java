package com.diplom.controller;

import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.service.DailyMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;

    @GetMapping("/dailyMenus/{id}")
    public DailyMenu getDailyMenuById(@PathVariable(value = "id") Integer dailyMenuId) {
        return dailyMenuService.getDailyMenu(dailyMenuId);
    }

    @PostMapping("/dailyMenus")
    public void addDailyMenu(DailyMenu dailyMenu) {
        dailyMenuService.saveDailyMenu(dailyMenu);
    }

    @DeleteMapping("dailyMenus/{id}")
    public void deleteDailyMenu (@PathVariable(value = "id") Integer dailyMenuId) {
        DailyMenu dailyMenu = dailyMenuService.getDailyMenu(dailyMenuId);
        dailyMenuService.deleteDailyMenu(dailyMenu);
    }
}
