package com.diplom.controller;

import com.diplom.dto.ProductDto;
import com.diplom.enums.Eating;
import com.diplom.model.Product;
import com.diplom.service.AddProductRequestService;
import com.diplom.service.CategoryService;
import com.diplom.service.DailyMenuService;
import com.diplom.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final DailyMenuService dailyMenuService;
    private final AddProductRequestService addProductRequestService;

    public ProductController(ProductService productService, CategoryService categoryService, DailyMenuService dailyMenuService, AddProductRequestService addProductRequestService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dailyMenuService = dailyMenuService;
        this.addProductRequestService = addProductRequestService;
    }

    //выводит все продукты
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "products/getProducts";
    }

    //показывает один продукт
    //@GetMapping("/{id}")
    //public String getProduct(@PathVariable("id") int id, Model model){
    //    model.addAttribute("product", productService.getProduct(id));
    //    return "products/showProduct";
    //}

    //выводит форму для создания продукта
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @GetMapping("/search")
    public String getProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "eating") String eating,
                             Model model,
                             Principal principal) {
        model.addAttribute("products", productService.getProducts(name));
        model.addAttribute("dailyMenu", dailyMenuService.getDailyMenuDto(principal.getName()));
        model.addAttribute("eating", eating);
        return "dailyMenu/addProductToDailyMenu";
    }

    //@GetMapping("/search")
    //public String getProduct(@RequestParam(value = "name") String name, Model model, Principal principal) {
    //    model.addAttribute("productRequest", addProductRequestService.addProductToRequest(name));
    //    //model.addAttribute("dailyMenu", dailyMenuService.getDailyMenu(principal.getName()));
    //    return "dailyMenu/addProductToDailyMenu";
    //}

    //создание продукта
    @PostMapping
    public String create(@ModelAttribute("product") @Valid ProductDto productDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/new";
        }
        productService.saveProduct(productDto);
        return "redirect:/products";
    }

    //форма для редактирования продукта
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productService.getProduct(id));
        return "products/edit";
    }

    //обновляет продукт
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") ProductDto productDto, @PathVariable("id") int id) {
        productService.updateProduct(id, productDto);
        return "redirect:/products";
    }

    //удаляет продукт
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}


