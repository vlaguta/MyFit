package com.diplom.utils;

import com.diplom.controller.dto.CommentDto;
import com.diplom.model.Comment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentConverter {

    public static CommentDto convertCommentEntityToCommentDto(Comment comment) {

        return CommentDto.builder()
                .text(comment.getText())
                .customer(comment.getCustomer())
                .photo(comment.getPhoto())
                .createdDate(comment.getCreatedDate())
                .build();
    }

    public static Comment convertCommentDtoToCommentEntity(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .customer(commentDto.getCustomer())
                .photo(commentDto.getPhoto())
                .createdDate(commentDto.getCreatedDate())
                .build();
    }
}
