package com.diplom.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Data
@Builder/*(toBuilder = true)*/
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    @Min(value=0, message = "Калорийность должна быть больше 0" )
    private int nominalCalories;
    @Min(value=0, message = "Количество жиров не может быть меньше 0")
    private int fat;
    @Min(value=0, message = "Количество белков не может быть меньше 0")
    private int protein;
    @Min(value=0, message = "Количество углеводов не может быть меньше 0")
    private int carbonhydrates;
    private int weight;
    private int factualCalories;
}
