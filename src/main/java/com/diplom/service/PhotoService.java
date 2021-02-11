package com.diplom.service;

import com.diplom.model.Photo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PhotoService {

    //public List<Photo> getAllPhotos();

    //public void savePhoto(Photo photo);

    //public void deletePhoto(Photo photo);

    public void init();

    public void save(MultipartFile file, String login);

    //public Resource load(String filename);
    public Photo load(String filename);

    //public void deleteAll();

    public Stream<Path> loadAll();

    Photo getPhoto(int customerId);

}
