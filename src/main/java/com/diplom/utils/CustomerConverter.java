package com.diplom.utils;

import com.diplom.dto.CustomerDto;
import com.diplom.dto.CustomerRegistrationDto;
import com.diplom.model.Customer;

public class CustomerConverter {

    public static CustomerDto convertCustomerEntityToCustomerDto(Customer customer){

        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .weight(customer.getWeight())
                .height(customer.getHeight())
                .activity(customer.getActivity())
                //.login(customer.getLogin())
                //.password(customer.getPassword())
                .roles(customer.getRoles())
                .sex(customer.getSex())
                .build();
    }

    public static Customer convertCustomerDtoToCustomerEntity(CustomerDto customerDto) {

        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .age(customerDto.getAge())
                .weight(customerDto.getWeight())
                .height(customerDto.getHeight())
                .activity(customerDto.getActivity())
                //.login(customerDto.getLogin())
                //.password(customerDto.getPassword())
                .roles(customerDto.getRoles())
                .sex(customerDto.getSex())
                .build();
    }

    public static CustomerRegistrationDto convertCustomerEntityToCustomerRegistrationDto(Customer customer){

        return CustomerRegistrationDto.builder()
                .id(customer.getId())
                .login(customer.getLogin())
                .password(customer.getPassword())
                .build();
    }

    public static Customer convertCustomerRegistrationDtoToCustomerEntity(CustomerRegistrationDto customerRegistrationDto) {

        return Customer.builder()
                .id(customerRegistrationDto.getId())
                .login(customerRegistrationDto.getLogin())
                .password(customerRegistrationDto.getPassword())
                .build();
    }
}
