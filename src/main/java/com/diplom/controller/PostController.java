package com.diplom.controller;

import com.diplom.dto.PostDto;
import com.diplom.model.Post;
import com.diplom.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/new")
    public String newPost(Model model){
        model.addAttribute("post", new Post());
        return "posts/new";
    }

    @PostMapping
    public String create(@ModelAttribute("post") PostDto postDto,
                         @RequestParam("file") MultipartFile file,
                         Principal principal){
        postService.savePost(file,postDto, principal.getName());
        return "redirect:/profile";
    }
}
