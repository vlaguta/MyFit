package com.diplom.service;

import com.diplom.AddProductRequest;
import com.diplom.dto.ProductDto;
import com.diplom.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddProductRequestService {

    private final ProductService productService;

    public AddProductRequest addProductToRequest(String name){
        List<ProductDto> products = productService.getProducts(name);
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductDto(products);
        return addProductRequest;
    }
}
