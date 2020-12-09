package com.diplom.repository;

import com.diplom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends  JpaRepository<Product, Integer> {
    Product findByName(String productName);

    Product deleteById(int id);
}
