package com.diplom.utils;

import com.diplom.dto.ProductDto;
import com.diplom.model.Product;

public class ProductConverter {

    public static ProductDto convertProductEntityToDto(Product product) {

        return  ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .calories(product.getCalories())
                .carbonhydrates(product.getCarbonhydrates())
                .fat(product.getFat())
                .protein(product.getProtein())
                .weight(product.getWeight())
                .build();
    }

    public static Product converterProductDtoToEntity(ProductDto productDto){

        return  Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .calories(productDto.getCalories())
                .carbonhydrates(productDto.getCarbonhydrates())
                .fat(productDto.getFat())
                .protein(productDto.getProtein())
                .weight(productDto.getWeight())
                .build();
    }
}
