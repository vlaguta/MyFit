package com.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "food_diary")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;

    @OneToOne(optional = false, mappedBy = "foodDiary")
    private Customer customer;

    // возможно еще нужны поля
}
