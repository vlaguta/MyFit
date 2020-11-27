package com.diplom.dto;

import com.diplom.Activity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerDto {

    private int id;
    private String name;
    private double weight;
    private double height;
    private int age;
    private Activity activity;
}
