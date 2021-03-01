package com.diplom.controller;

import com.diplom.dto.ProductDto;
import com.diplom.enums.Eating;
import com.diplom.model.Product;
import com.diplom.service.CustomerService;
import com.diplom.service.DailyMenuService;
import com.diplom.service.ProductDailyMenuService;
import com.diplom.service.ProductService;
import com.diplom.utils.EnumConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final ProductDailyMenuService productDailyMenuService;

    @GetMapping("/daily-menus")
    public String getDailyMenuById(Model model, Principal principal) {
        model.addAttribute("dailyMenu", dailyMenuService.getDailyMenu(principal.getName()));
        model.addAttribute("з", dailyMenuService.getDailyMenu(principal.getName()).getBreakfast().get(0));
        return "dailyMenu/getDailyMenu";
    }

    @GetMapping("/add")
    public String getDailyMenuById(@RequestParam("eating") String eating, Model model) {
        model.addAttribute("eating", eating);
        return "dailyMenu/addProductToDailyMenu";
    }

    @PostMapping("/daily-menus/{dailyMenuId}/products")
    public String addDailyMenu(@ModelAttribute("product") ProductDto product,
                               @ModelAttribute("eating") String eating,
                               @PathVariable("dailyMenuId") int dailyMenuId) {
        dailyMenuService.addProductToDailyMenu(dailyMenuId, product, Eating.valueOf("BREAKFAST"));
        return "redirect:/daily-menus";
    }

    //удаление дэйли меню
    @DeleteMapping("/daily-menus/{id}")
    public void deleteDailyMenu(@PathVariable(value = "id") Integer dailyMenuId) {
        dailyMenuService.deleteDailyMenu(dailyMenuId);
    }
}
