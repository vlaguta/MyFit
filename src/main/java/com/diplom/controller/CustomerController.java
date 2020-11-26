package com.diplom.controller;

import com.diplom.model.Customer;
import com.diplom.model.Product;
import com.diplom.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> all() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public void addCustomer(Customer customer) {
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("customers/{id}")
    public void deleteCustomer(@PathVariable("id") Integer customerId) {
        Customer customer = customerService.getCustomer(customerId);
        customerService.deleteCustomer(customer);
    }

//    @PutMapping("customers/{id}")
//    public void updateCustomer(
//            @PathVariable(value="id") Integer customerId, Product newProduct){
//        Product product = productService.getProduct(productId);
//        //хз
//    }

}
