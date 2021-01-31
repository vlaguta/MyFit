package com.diplom.controller;

import com.diplom.dto.CustomerDto;
import com.diplom.dto.ProductDto;
import com.diplom.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable("id") Integer customerId, Model model) {
        model.addAttribute("user",customerService.getCustomer(customerId));
        return "userProfile/userProfile";
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "userProfile/userProfile";
    }

    @GetMapping("/login")
    public String LoginForm(Model model) {
        return "security/login";
    }

    //@PatchMapping("/{id}")
    //public String update(@ModelAttribute("product") CustomerDto customerDto, @PathVariable("id") int id){
    //    customerService.updateCustomer(customerDto);
    //    return "redirect:/products";
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

