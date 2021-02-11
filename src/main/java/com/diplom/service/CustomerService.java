package com.diplom.service;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    boolean saveCustomer(CustomerDto customerDto);

    public void updateCustomer(int id, CustomerDto customerDto);

    public CustomerDto getCustomer(String login);

    public double getWeightLossCalories(CustomerDto customer);
    public double getWeightGainCalories(CustomerDto customer);
    public double getWeightMaintainCalories(CustomerDto customer);
    public double getBasicMetabolism(CustomerDto customer);

}
