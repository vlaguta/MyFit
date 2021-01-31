package com.diplom.controller;

import com.diplom.dto.DailyMenuDto;
import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.service.DailyMenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequiredArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;

    @GetMapping("/daily-menus/{id}")
    public String getDailyMenuById(@PathVariable(value = "id") Integer dailyMenuId) {
        return "dailyMenu";
    }

    @PostMapping("/daily-menus")
    public void addDailyMenu(DailyMenuDto dailyMenuDto) {
        dailyMenuService.saveDailyMenu(dailyMenuDto);
    }

    @DeleteMapping("/daily-menus/{id}")
    public void deleteDailyMenu (@PathVariable(value = "id") Integer dailyMenuId) {
        dailyMenuService.deleteDailyMenu(dailyMenuId);
    }

    @PutMapping("/daily-menus/{id}")
    public void updateDailyMenu(@PathVariable(value="id")Integer dailyMenuId, List<Product> products){
        dailyMenuService.updateDailyMenu(dailyMenuId,products);
    }
}
