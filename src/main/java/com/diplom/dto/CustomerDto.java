package com.diplom.dto;

import com.diplom.Activity;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;
    private String name;
    private double weight;
    private double height;
    private int age;
    private Activity activity;
}
