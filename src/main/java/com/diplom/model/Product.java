package com.diplom.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "product")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int calories;
    private int fat;
    private int protein;
    private int carbonhydrates;
    private int weight;
    @ManyToMany (mappedBy = "products")
    private List<DailyMenu> dailyMenus;

    public Product() {
    }
}
