package com.diplom.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Table(name = "product")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToMany(mappedBy = "products")
    private List<DailyMenu> dailyMenus;
}
