package com.diplom.controller;

import com.diplom.dto.DailyMenuDto;
import com.diplom.dto.ProductDto;
import com.diplom.model.DailyMenu;
import com.diplom.service.CustomerService;
import com.diplom.service.DailyMenuService;
import com.diplom.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;
    private final CustomerService customerService;
    private final ProductService productService;

    @GetMapping("/daily-menus")
    public String getDailyMenuById(Model model, Principal principal) {
        model.addAttribute("dailyMenu", dailyMenuService.getDailyMenu(principal.getName()));
        return "dailyMenu/getDailyMenu";
    }

    @GetMapping("/add")
    public String getDailyMenuById() {
        return "dailyMenu/addProductToDailyMenu";
    }

    //@PatchMapping("/daily-menus/{dailyMenuId}/products/{productId}")
    //public String addDailyMenu(@PathVariable("dailyMenuId") int dailyMenuId,
    //                         @PathVariable("productId")int productId,
    //                           Model model) {
    //    dailyMenuService.addProductDailyMenu(productId, dailyMenuId);
    //    return "redirect:/daily-menus";
    //}

    //@GetMapping("/daily-menus/{dailyMenuId}/products/{productId}")
    //public String getDailyMenuById(@RequestParam ProductDto productDto, Model model, Principal principal) {
    //    model.addAttribute("dailyMenu", dailyMenuService.getDailyMenu(principal.getName()));
    //    model.addAttribute("product", productService.getProducts(productDto.getName()));
    //    return "dailyMenu/addProductToDailyMenu";
    //}
    //
    //@PatchMapping("/daily-menus")
    //public String addDailyMenu(
    //        @ModelAttribute("dailyMenu") DailyMenuDto dailyMenu)
    //         {
    //    dailyMenuService.saveDailyMenu(dailyMenu);
    //    return "redirect:/daily-menus";
    //}

    //удаление дэйли меню
    @DeleteMapping("/daily-menus/{id}")
    public void deleteDailyMenu(@PathVariable(value = "id") Integer dailyMenuId) {
        dailyMenuService.deleteDailyMenu(dailyMenuId);
    }
}
