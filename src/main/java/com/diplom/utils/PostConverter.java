package com.diplom.utils;

import com.diplom.dto.PostDto;
import com.diplom.model.Post;

public class PostConverter {

    public static PostDto convertPostEntityToPostDto(Post post) {

            return PostDto.builder()
                    .text(post.getText())
                    .customer(post.getCustomer())
                    //.comments(post.getComments())
                    .photo(post.getPhoto())
                    .createdDate(post.getCreatedDate())
                    .build();
        }

    public static Post convertPostDtoToPostEntity(PostDto postDto) {
        return Post.builder()
                .text(postDto.getText())
                .customer(postDto.getCustomer())
                //.comments(post.getComments())
                .photo(postDto.getPhoto())
                .createdDate(postDto.getCreatedDate())
                .build();
    }
}