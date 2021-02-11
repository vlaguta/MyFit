package com.diplom.service;

import com.diplom.model.Customer;
import com.diplom.model.Photo;
import com.diplom.repository.CustomerRepository;
import com.diplom.repository.PhotoRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final CustomerRepository customerRepository;

    //public List<Photo> getAllPhotos(){
    //    return photoRepository.findAll();
    //}

    //public void savePhoto(Photo photo){
    //    photoRepository.save(photo);
    //}

    //public void deletePhoto(Photo photo){
    //    photoRepository.delete(photo);
    //}

    private final Path PhotoDirectoryPath = Paths.get("src/main/resources/static");

    public PhotoServiceImpl(PhotoRepository photoRepository, CustomerRepository customerRepository) {
        this.photoRepository = photoRepository;
        this.customerRepository = customerRepository;
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
    public void save(MultipartFile file, String login) {
        try {
            Files.copy(file.getInputStream(), this.PhotoDirectoryPath.resolve(file.getOriginalFilename()));

            Photo photo = photoRepository.findByCustomerLogin(login)
                    .orElse( Photo.builder()
                            .customer(customerRepository.findCustomerByLogin(login)
                            .orElse(null))
                            .build());
            //photo.setCustomer(customer);
            photo.setName(file.getOriginalFilename());
            photo.setUrl("/" + file.getOriginalFilename());
            photoRepository.save(photo);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());

        }
    }

    @Override
    public Photo load(String filename) {
        Photo photo = photoRepository.findByName(filename).orElse(null);
        return photo;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.PhotoDirectoryPath, 1).filter(path -> !path.equals(this.PhotoDirectoryPath)).map(this.PhotoDirectoryPath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public Photo getPhoto(int customerId) {
        return photoRepository.findByCustomerId(customerId).orElse(null);
    }
}
