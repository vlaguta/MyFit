package com.diplom.service;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Customer;
import com.diplom.model.Role;
import com.diplom.repository.CustomerRepository;
import com.diplom.utils.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.diplom.utils.CustomerConverter.convertCustomerDtoToCustomerEntity;
import static com.diplom.utils.CustomerConverter.convertCustomerEntityToCustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerConverter::convertCustomerEntityToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomer(int id) {
        return convertCustomerEntityToCustomerDto(customerRepository.getOne(id));
    }

    //public void saveCustomer(CustomerDto customerdto){
    //
    //    customerRepository.save(convertCustomerDtoToCustomerEntity(customerdto));
    //}

    @Override
    public boolean saveCustomer(CustomerDto customerdto) {

        Customer customer = convertCustomerDtoToCustomerEntity(customerdto);
        Customer customerFromBd = customerRepository.findCustomerByLogin(customer.getLogin());

        if (customerFromBd != null) {
            return false;
        }

        customer.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return true;
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(CustomerDto customerDto) {
        customerRepository.save(convertCustomerDtoToCustomerEntity(customerDto));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return customerRepository.findCustomerByLogin(login);
    }
}
