package com.diplom.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private int calories;
    private int fat;
    private int protein;
    private int carbonhydrates;
    private int weight;
}
