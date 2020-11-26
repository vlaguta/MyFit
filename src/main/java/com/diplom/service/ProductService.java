package com.diplom.service;

import com.diplom.model.Product;
import com.diplom.repository.ProductRepository;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProduct(int id){
        return productRepository.getOne(id);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

//    public void updateProduct(){
//        productRepository.
//    }
}

