package com.diplom.controller;

import com.diplom.dto.CustomerDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

public class RegistrationController {

    //@GetMapping("/registration")
    //public String showRegistrationForm(WebRequest request, Model model) {
    //    CustomerDto customerDto = new CustomerDto();
    //    model.addAttribute("user", customerDto);
    //    return "registration";
    //}
    //
    //@PostMapping("/registration")
    //public String addUser(@ModelAttribute("user`") @Valid User userForm, BindingResult bindingResult, Model model) {
    //
    //    if (bindingResult.hasErrors()) {
    //        return "registration";
    //    }
    //    if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
    //        model.addAttribute("passwordError", "Пароли не совпадают");
    //        return "registration";
    //    }
    //    if (!userService.saveUser(userForm)){
    //        model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
    //        return "registration";
    //    }
    //
    //    return "redirect:/";
    }
