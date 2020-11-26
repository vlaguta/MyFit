package com.diplom.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "post_photo",
          joinColumns ={@JoinColumn(name="post_id")},
          inverseJoinColumns = {@JoinColumn(name = "photo_id")}
  )
    private List<Photo> photos;

    @OneToMany(mappedBy = "post")
    private List<Comment>comments;


}
