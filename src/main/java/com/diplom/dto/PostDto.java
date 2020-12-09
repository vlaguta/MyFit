package com.diplom.dto;

import com.diplom.model.Comment;
import com.diplom.model.Customer;
import com.diplom.model.Photo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

        private List<Photo> photos;
        private List<Comment> comments;
        private String text;
    }
