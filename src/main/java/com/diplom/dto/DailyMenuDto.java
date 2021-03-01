package com.diplom.dto;

import com.diplom.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyMenuDto {

    //private List<Product> products;
    //private LocalDate createdDate;
    private int id;
    //private String name;
    private List<ProductDto> breakfast;
    private List<ProductDto> dinner;
    private List<ProductDto> supper;
    private int generalCalories;
    private int generalProteins;
    private int generalFats;
    private int generalCarbonhydrates;


    // private Customer customer; // не уверена, что нужно это поле в дто
}
