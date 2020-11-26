package com.diplom.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "daily_menu")
public class DailyMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerId;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_daily_menu",
            joinColumns = @JoinColumn(name = "daily_menu_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToOne(optional = false, mappedBy = "dailyMenu")
   private Customer customer;

}
