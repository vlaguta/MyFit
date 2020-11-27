package com.diplom.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDto {

    private String name;
    private int calories;
    private int fat;
    private int protein;
    private int carbonhydrates;
    private int weight;
}
