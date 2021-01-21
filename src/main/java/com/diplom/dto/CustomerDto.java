package com.diplom.dto;

import com.diplom.enums.Activity;
import com.diplom.model.Role;
import lombok.*;

import java.util.Set;

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
    private String login;
    private String password;
    private Set<Role> roles;
}
