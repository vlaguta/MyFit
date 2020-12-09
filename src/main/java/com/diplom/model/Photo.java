package com.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="photo")
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "photo")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "photos")
    private List<Post>posts;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
