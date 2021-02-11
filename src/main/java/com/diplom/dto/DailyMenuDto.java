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

    private List<Product> products;
    private LocalDate createdDate;
    private int id;
    private String name;


    // private Customer customer; // не уверена, что нужно это поле в дто
}
