package com.diplom.dto;

import com.diplom.model.Customer;
import com.diplom.model.Product;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyMenuDto {

        private List<Product> products;

       // private Customer customer; // не уверена, что нужно это поле в дто
    }
