package com.diplom.controller;

import com.diplom.dto.CustomerDto;
import com.diplom.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private CustomerService customerService;

    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("customerForm", new CustomerDto());
        return "security/registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("customerForm") CustomerDto customerForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "security/registration";

        }
        if (customerService.saveCustomer(customerForm))
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
        return "profile";
    }
}
