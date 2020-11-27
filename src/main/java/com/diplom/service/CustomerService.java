package com.diplom.service;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Customer;
import com.diplom.repository.CustomerRepository;

import com.diplom.utils.CustomerConverter;
import static com.diplom.utils.CustomerConverter.convertCustomerEntityToCustomerDto;
import static com.diplom.utils.CustomerConverter.convertCustomerDtoToCustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomer(){
        return customerRepository.findAll()
                .stream()
                .map(CustomerConverter::convertCustomerEntityToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomer(int id){
        return convertCustomerEntityToCustomerDto(customerRepository.getOne(id));
    }

    public void saveCustomer(CustomerDto customerdto){
        customerRepository.save(convertCustomerDtoToCustomerEntity(customerdto));
    }

    public void deleteCustomer(int id){
        customerRepository.deleteById(id);
    }

    public void updateCustomer(CustomerDto customerDto){
        customerRepository.save(convertCustomerDtoToCustomerEntity(customerDto));
    }
}

