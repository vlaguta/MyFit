package com.diplom.service;

import com.diplom.model.Photo;
import com.diplom.repository.PhotoRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    //public List<Photo> getAllPhotos(){
    //    return photoRepository.findAll();
    //}

    //public void savePhoto(Photo photo){
    //    photoRepository.save(photo);
    //}

    //public void deletePhoto(Photo photo){
    //    photoRepository.delete(photo);
    //}

    private final Path PhotoDirectoryPath = Paths.get("src/main/resources/uploads");

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        init();
    }

    @Override
    public void init() {
        if (Files.exists(PhotoDirectoryPath)) {
            return;
        }
        try {
            Files.createDirectory(PhotoDirectoryPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.PhotoDirectoryPath.resolve(file.getOriginalFilename()));
            Photo photo = new Photo();
            photo.setName(file.getOriginalFilename());
            photo.setUrl("/src/main/resources/uploads/" + file.getOriginalFilename());
            photoRepository.save(photo);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());

        }
    }

    //@Override
    //public Resource load(String filename) {
    //    try {
    //        Path file = root.resolve(filename);
    //        Resource resource = new UrlResource(file.toUri());
    //
    //        if (resource.exists() || resource.isReadable()) {
    //            return resource;
    //        } else {
    //            throw new RuntimeException("Could not read the file!");
    //        }
    //    } catch (MalformedURLException e) {
    //        throw new RuntimeException("Error: " + e.getMessage());
    //    }
    //}

    @Override
    public Photo load(String filename) {
        try {
            Photo photo = photoRepository.findByName(filename);
            Path file = PhotoDirectoryPath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return photo;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    //@Override
    //public void deleteAll() {
    //    FileSystemUtils.deleteRecursively(root.toFile());
    //}

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.PhotoDirectoryPath, 1).filter(path -> !path.equals(this.PhotoDirectoryPath)).map(this.PhotoDirectoryPath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
