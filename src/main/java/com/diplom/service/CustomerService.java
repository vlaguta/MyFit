package com.diplom.service;

import com.diplom.model.Customer;
import com.diplom.model.Product;
import com.diplom.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id){
        return customerRepository.getOne(id);
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
}
