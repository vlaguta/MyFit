package com.diplom.dto;

import com.diplom.enums.Activity;
import com.diplom.enums.Sex;
import com.diplom.model.Role;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;
    private String name;
    @Min(value = 0, message = "Вес должен быть больше 0")
    private int weight;
    @Min(value = 0, message = "Рост должен быть больше 0")
    private int height;
    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;
    private Sex sex;
    private Activity activity;
    @Size(min=6, max = 15, message = "Количество знаков должно быть в диапазоне от 6 до 15")
    //@NotEmpty(message = "Поле должно быть заполнено")
    private String login;
    @Size(min=5, message = "Минимальное количество знаков - 5")
    //@NotEmpty(message = "Поле должно быть заполнено")
    private String password;
    private Set<Role> roles;
    private double basicMetabolism;
    private double weightLossCalories;
    private double weightGainCalories;
    private double weightMaintainCalories;
}
