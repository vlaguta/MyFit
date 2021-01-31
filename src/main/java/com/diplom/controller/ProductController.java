package com.diplom.controller;

import com.diplom.dto.ProductDto;
import com.diplom.model.Product;
import com.diplom.service.CategoryService;
import com.diplom.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    //выводит все продукты
    @GetMapping
    public String getAllProducts(Model model){
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "products/getProducts";
    }

    //показывает один продукт
    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "products/showProduct";
    }

    //выводит форму для создания продукта
    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "products/new";
    }

    //создание продукта
    @PostMapping
    public String create(@ModelAttribute("product") ProductDto productDto){
        productService.saveProduct(productDto);
        return "redirect:/products";
    }

    //форма для редактирования продукта
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProduct(id));
        return "products/edit";
    }

    //обновляет продукт
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") ProductDto productDto, @PathVariable("id") int id){
        productService.updateProduct(id, productDto);
        return "redirect:/products";
    }

    //удаляет продукт
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}


