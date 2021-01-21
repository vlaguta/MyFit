package com.diplom.controller;

import com.diplom.dto.CustomerDto;
import com.diplom.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/login")
    public String LoginForm(Model model) {
        return "security/login";
    }

    //@PostMapping("/login")
    //public CustomerDto customerDto(@RequestBody CustomerDto customerDto) {
    //    //return customerService.
    //}


    //@PostMapping
    //public void addCustomer(CustomerDto customerDto) {
    //    customerService.saveCustomer(customerDto);
    //}

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/{id}")
    public void updateCustomer(
            @PathVariable(value = "id") CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
    }
}

