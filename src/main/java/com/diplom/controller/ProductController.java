package com.diplom.controller;

import com.diplom.model.Product;
import com.diplom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> all() {
        return productService.getAllProduct();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/products")
    public void addProduct(Product product) {
        productService.saveProduct(product);
    }

//    @DeleteMapping("products/{id}")
//    public void deleteProduct(@PathVariable(value = "id") Integer productId) {
//        Product product = productService.getProduct(productId);
//        productService.deleteProduct(); //удалять по id
//    }

    @PutMapping("products/{id}")
    public void updateProduct(@PathVariable(value = "id") Integer productId, Product newProduct) {
        Product product = productService.getProduct(productId);
        //хз
    }
}
