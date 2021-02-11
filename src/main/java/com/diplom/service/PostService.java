package com.diplom.service;

import com.diplom.dto.PostDto;
import com.diplom.model.Post;
import com.diplom.repository.CustomerRepository;
import com.diplom.repository.PostRepository;
import com.diplom.utils.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CustomerRepository customerRepository;

    public void savePost(PostDto postDto, String login) {
        postDto.setCreatedDate(LocalDateTime.now());
        postDto.setCustomer(customerRepository.findCustomerByLogin(login).orElse(null));
        postRepository.save(PostConverter.convertPostDtoToPostEntity(postDto));
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public void update() {
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Post::getCreatedDate)
                            .reversed())
                    .collect(Collectors.toList());
        }
    }
