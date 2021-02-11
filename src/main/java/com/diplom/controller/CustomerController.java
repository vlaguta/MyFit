package com.diplom.controller;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Customer;
import com.diplom.service.CustomerService;
import com.diplom.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final PhotoService photoService;

    @GetMapping("/profile")
    public String userProfile(Principal principal, Model model) {
        String login = principal.getName();
        CustomerDto customer = customerService.getCustomer(login);
        model.addAttribute("user", customer);
        model.addAttribute("photo", photoService.getPhoto(customer.getId()));
        return "userProfile/userProfile";
    }

    @GetMapping("/login")
    public String LoginForm(Model model) {
        return "security/login";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") CustomerDto customerDto, @PathVariable("id") int id){
        customerService.updateCustomer(id, customerDto);
        return "redirect:/customers/profile";
    }

    //@PatchMapping("/{id}")
    //public String update(@ModelAttribute("product") CustomerDto customerDto, @PathVariable("id") int id){
    //    customerService.updateCustomer(customerDto);
    //    return "redirect:/products";
    //}
}

