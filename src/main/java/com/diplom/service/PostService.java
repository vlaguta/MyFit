package com.diplom.service;

import com.diplom.model.Post;
import com.diplom.model.Product;
import com.diplom.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePost(Post post){
        postRepository.save(post);
    }

    public void deletePost(Post post){
        postRepository.delete(post);
    }

    public void update(){
    }

    public  List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
