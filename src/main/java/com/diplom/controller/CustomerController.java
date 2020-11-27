package com.diplom.controller;

import com.diplom.dto.CustomerDto;
import com.diplom.dto.ProductDto;
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
    public List<CustomerDto> all() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public void addCustomer(CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
    }

    @DeleteMapping("customers/{id}")
    public void deleteCustomer(@PathVariable("id") Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("customers/{id}")
    public void updateCustomer(
            @PathVariable(value="id") CustomerDto customerDto){
        customerService.saveCustomer(customerDto);
    }
}
