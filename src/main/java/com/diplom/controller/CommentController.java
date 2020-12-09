package com.diplom.controller;

import com.diplom.model.Comment;
import com.diplom.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public void addComment(Comment comment) {
        commentService.saveComment(comment);
    } //криэйт

    @DeleteMapping("comments/{id}")
    public void deleteComment (
            @PathVariable(value = "id") Integer commentId) {
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(comment);
    }
}
