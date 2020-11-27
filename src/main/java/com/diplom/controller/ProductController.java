package com.diplom.controller;

import com.diplom.dto.ProductDto;
import com.diplom.model.Product;
import com.diplom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @GetMapping("/products")
//    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model
//            model) {
//        model.addAttribute("name", name);
//        return "myFit";
//    }

    @GetMapping("/products")
    public List<ProductDto> all() {
        return productService.getAllProduct();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable(value = "id") Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable(value = "id") Integer productId) {
        productService.deleteProduct(productId);
    }


    @PutMapping("products/{id}")
    public void updateProduct(@PathVariable(value = "id") ProductDto productDto) {
    productService.saveProduct(productDto);
    }
}

