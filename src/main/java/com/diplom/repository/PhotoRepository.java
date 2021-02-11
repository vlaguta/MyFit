package com.diplom.repository;

import com.diplom.model.Customer;
import com.diplom.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {

    Optional<Photo> findByName(String name);

    Optional<Photo> findByCustomerId(int customerId);

    Optional<Photo> findByCustomerLogin(String login);
}
