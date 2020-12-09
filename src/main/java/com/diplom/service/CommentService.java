package com.diplom.service;

import com.diplom.model.Comment;
import com.diplom.model.Product;
import com.diplom.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment getComment(int id){
        return commentRepository.getOne(id);
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }

    public void updateComment(Comment comment){
       // здесь будет приходить объект коммент или можно через сеттер апдейтить текст
    }
}
