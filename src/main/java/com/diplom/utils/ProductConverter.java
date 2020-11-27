package com.diplom.utils;

import com.diplom.dto.ProductDto;
import com.diplom.model.Product;

public class ProductConverter {

    public static ProductDto convertProductEntityToDto(Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setCalories(product.getCalories());
        productDto.setCarbonhydrates(product.getCarbonhydrates());
        productDto.setFat(product.getFat());
        productDto.setProtein(product.getProtein());
        productDto.setWeight(product.getWeight());

        return productDto;
    }

    public static Product converterProductDtoToEntity(ProductDto productDto){

        Product product = new Product();

        product.setName(productDto.getName());
        product.setCalories(productDto.getCalories());
        product.setCarbonhydrates(productDto.getCarbonhydrates());
        product.setFat(productDto.getFat());
        product.setProtein(productDto.getProtein());
        product.setWeight(productDto.getWeight());

        return product;
    }
}
