package com.diplom.service;

import com.diplom.model.Photo;
import com.diplom.model.Product;
import com.diplom.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }

   // public Photo getPhoto(int id){
//        return photoRepository.getOne(id);
        //хз что тут
 //   }
    public void savePhoto(Photo photo){
        photoRepository.save(photo);
    }

    public void deletePhoto(Photo photo){
        photoRepository.delete(photo);
    }
}
