package com.diplom.service;

import com.diplom.dto.PostDto;
import com.diplom.model.Photo;
import com.diplom.model.Post;
import com.diplom.repository.CustomerRepository;
import com.diplom.repository.PhotoRepository;
import com.diplom.repository.PostRepository;
import com.diplom.utils.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CustomerRepository customerRepository;
    private final PhotoRepository photoRepository;
    private final Path PhotoDirectoryPath = Paths.get("src/main/resources/static");

    public void savePost(MultipartFile file, PostDto postDto, String login) {
        try {
            Files.copy(file.getInputStream(), this.PhotoDirectoryPath.resolve(file.getOriginalFilename()));

            Photo photo = new Photo();
            photo.setName(file.getOriginalFilename());
            photo.setUrl("/" + file.getOriginalFilename());
            //photo.setPost(PostConverter.convertPostDtoToPostEntity(postDto)); //так можно делать?
            photoRepository.save(photo);
            postDto.setCreatedDate(LocalDateTime.now());
            postDto.setCustomer(customerRepository.findCustomerByLogin(login).orElse(null));
            postDto.setPhoto(photo);
            postRepository.save(PostConverter.convertPostDtoToPostEntity(postDto));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

        public void deletePost (Post post){
            postRepository.delete(post);
        }

        public void update () {
        }

        public List<Post> getAllPosts () {
            return postRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Post::getCreatedDate)
                            .reversed())
                    .collect(Collectors.toList());
        }
    }


