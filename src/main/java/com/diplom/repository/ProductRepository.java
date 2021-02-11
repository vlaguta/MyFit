package com.diplom.repository;

import com.diplom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String productName);

    Product deleteById(int id);

    @Override
    List<Product> findAll();

    Product findById(int id);
}
