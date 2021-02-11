package com.diplom.utils;

import com.diplom.dto.CustomerDto;
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
                .login(customer.getLogin())
                .password(customer.getPassword())
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
                .login(customerDto.getLogin())
                .password(customerDto.getPassword())
                .roles(customerDto.getRoles())
                .sex(customerDto.getSex())
                .build();
    }
}
