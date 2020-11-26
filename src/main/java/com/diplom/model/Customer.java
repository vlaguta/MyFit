package com.diplom.model;

import com.diplom.Activity;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double weight;
    private double height;
    private int age;
    private Activity activity;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "daily_menu_id")
    private DailyMenu dailyMenu;
//    @OneToMany(mappedBy = "customer")
//    private List<Post> postList;

    @OneToMany(mappedBy = "customer")
    private List<Post>posts;

    @OneToMany(mappedBy = "customer")
    private List <Photo> photos;
}
