package com.diplom.controller;

import com.diplom.dto.ProductDto;
import com.diplom.service.ProductService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    private final ProductService productService;

    public Controller(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("list")
//    ModelAndView list() {
//        Map<String, List<ProductDto>> map = new HashMap<>();
//        return new ModelAndView("/list",
//                map.put("products", productService.getAllProduct()),
//                HttpStatus.OK);
//    }
}

