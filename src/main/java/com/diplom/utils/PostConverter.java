package com.diplom.utils;

import com.diplom.dto.PostDto;
import com.diplom.model.Post;

public class PostConverter {

    public PostDto convertPostEntityToPostDto(Post post) {

            return PostDto.builder()
                    .text(post.getText())
                    //.comments(post.getComments())
                    .photos(post.getPhotos())
                    .build();
        }
    }

