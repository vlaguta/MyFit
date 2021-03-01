package com.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    @ManyToMany(mappedBy = "products")
    private List<DailyMenu> dailyMenus; //скорее всего не будет использоваться

    //@ManyToMany(cascade = CascadeType.ALL)
    //@JoinTable(name = "product_daily_menu",
    //        joinColumns = @JoinColumn(name = "product_id"),
    //        inverseJoinColumns = @JoinColumn(name = "daily_menu_id"))
    //private List<DailyMenu> dailyMenus;
}
