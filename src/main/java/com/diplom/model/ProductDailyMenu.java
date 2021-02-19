package com.diplom.model;

import com.diplom.enums.EatingEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="product_daily_menu")
public class ProductDailyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="daily_menu_id")
    private int dailyMenuId;

    @Column(name="eating")
    private EatingEnum eating;


}
