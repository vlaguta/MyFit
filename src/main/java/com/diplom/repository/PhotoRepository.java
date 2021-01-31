package com.diplom.repository;

import com.diplom.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {

    public Photo findByName(String name);

}
