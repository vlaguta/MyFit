package com.diplom.service;

import com.diplom.dto.CustomerDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    boolean saveCustomer(CustomerDto customerDto);
}
