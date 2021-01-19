package com.diplom.configuration;

import com.diplom.model.Customer;
import com.diplom.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public UserDetailServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByName(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(customer.getName())
                .password(customer.getPassword())
                .authorities(customer.getName())
                .build();
    }
}
