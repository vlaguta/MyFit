package com.diplom.utils;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Customer;

public class CustomerConverter {

    public static CustomerDto convertCustomerEntityToCustomerDto(Customer customer){

        CustomerDto customerDto = new CustomerDto();

        customerDto.setName(customer.getName());
        customerDto.setAge(customer.getAge());
        customerDto.setWeight(customer.getWeight());
        customerDto.setHeight(customer.getHeight());
        customerDto.setActivity(customer.getActivity());

        return customerDto;
    }

    public static Customer convertCustomerDtoToCustomerEntity(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setAge(customerDto.getAge());
        customer.setWeight(customerDto.getWeight());
        customer.setHeight(customerDto.getHeight());
        customer.setActivity(customerDto.getActivity());

        return customer;
    }
}
