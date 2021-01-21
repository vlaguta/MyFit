package com.diplom.repository;

import com.diplom.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
 public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public void deleteById(int id);

    public Customer findCustomerByName(String name);

    public Customer findCustomerByLogin(String login);
}
