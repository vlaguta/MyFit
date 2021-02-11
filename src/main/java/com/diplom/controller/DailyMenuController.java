package com.diplom.controller;

import com.diplom.dto.DailyMenuDto;
import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.service.CustomerService;
import com.diplom.service.DailyMenuService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;
    private final CustomerService customerService;

    @GetMapping("/daily-menus")
    public String getDailyMenuById(  Model model, Principal principal) {
        DailyMenuDto dailyMenu=dailyMenuService.getDailyMenu(principal.getName());
        model.addAttribute("dailyMenu", dailyMenu);
        model.addAttribute("break", dailyMenuService.get(principal.getName()));
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
