package com.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
  //  private  тип для даты

  @ManyToOne
  @JoinColumn(name="post_id")
    private Post post;

  @ManyToOne
    @JoinColumn(name="photo_id")
    private Photo photo;

}
