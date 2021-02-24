package com.diplom.service;

import com.diplom.dto.CustomerDto;
import com.diplom.dto.CustomerRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    //boolean saveCustomer(CustomerDto customerDto);

    boolean saveCustomer(CustomerRegistrationDto customerRegistrationDto);

    public void updateCustomer(int id, CustomerDto customerDto);

    public CustomerDto getCustomer(String login);

    public CustomerDto getCustomer(int id);

    public double getWeightLossCalories(CustomerDto customer);

    public double getWeightGainCalories(CustomerDto customer);

    public double getWeightMaintainCalories(CustomerDto customer);

    public double getBasicMetabolism(CustomerDto customer);

}
