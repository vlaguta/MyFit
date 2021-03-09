package com.diplom.service;

import com.diplom.Exceptions.BusinessException;
import com.diplom.dto.CustomerDto;
import com.diplom.dto.CustomerRegistrationDto;
import com.diplom.enums.Activity;
import com.diplom.enums.Sex;
import com.diplom.model.Customer;
import com.diplom.model.Role;
import com.diplom.repository.CustomerRepository;
import com.diplom.utils.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.diplom.utils.CoefficientUtils.AGE_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.HEIGHT_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.HIGH_ACTIVITY_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.LOW_ACTIVITY_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.MEDIUM_ACTIVITY_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.MEN_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.WEIGHT_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.WEIGHT_GAIN_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.WEIGHT_LOSS_COEFFICIENT;
import static com.diplom.utils.CoefficientUtils.WOMEN_COEFFICIENT;
import static com.diplom.utils.CustomerConverter.convertCustomerDtoToCustomerEntity;
import static com.diplom.utils.CustomerConverter.convertCustomerEntityToCustomerDto;
import static com.diplom.utils.CustomerConverter.convertCustomerRegistrationDtoToCustomerEntity;

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

    public CustomerDto getCustomer(String login) {
        CustomerDto customerDto = convertCustomerEntityToCustomerDto(customerRepository.findCustomerByLogin(login).orElse(null));
        customerDto.setBasicMetabolism(getBasicMetabolism(customerDto));
        customerDto.setWeightLossCalories(getWeightLossCalories(customerDto));
        customerDto.setWeightGainCalories(getWeightGainCalories(customerDto));
        customerDto.setWeightMaintainCalories(getWeightMaintainCalories(customerDto));
        return customerDto;
    }

    public CustomerDto getCustomer(int id) {
        CustomerDto customerDto = convertCustomerEntityToCustomerDto(customerRepository.findById(id).orElse(null));
        customerDto.setBasicMetabolism(getBasicMetabolism(customerDto));
        customerDto.setWeightLossCalories(getWeightLossCalories(customerDto));
        customerDto.setWeightGainCalories(getWeightGainCalories(customerDto));
        customerDto.setWeightMaintainCalories(getWeightMaintainCalories(customerDto));
        return customerDto;
    }

    @Override
    public boolean saveCustomer(CustomerRegistrationDto customerRegistrationDto) {

        Customer customer = convertCustomerRegistrationDtoToCustomerEntity(customerRegistrationDto);
        Customer customerFromBd = customerRepository.findCustomerByLogin(customer.getLogin()).orElse(null);

        if (customerFromBd != null) {
            return false;
        }
        customer.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return true;
    }

    @Override
    @Transactional
    public void updateCustomer(int id, CustomerDto customerDto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Невозможно обновить пользователя. Пользователь не найден"));
        if (customerDto.getSex() != null) {
            customer.setSex(customerDto.getSex());
        }
        if (customerDto.getAge() != 0) {
            customer.setAge(customerDto.getAge());
        }
        if (customerDto.getWeight() != 0) {
            customer.setWeight(customerDto.getWeight());
        }
        if (customerDto.getHeight() != 0) {
            customer.setHeight(customerDto.getHeight());
        }
        if (customerDto.getActivity() != null) {
            customer.setActivity(customerDto.getActivity());
        }
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return customerRepository.findCustomerByLogin(login).orElse(null);
    }

    public double getBasicMetabolism(CustomerDto customerDto) {
        if (Sex.WOMEN == customerDto.getSex()) {
            return WEIGHT_COEFFICIENT * customerDto.getWeight() + HEIGHT_COEFFICIENT * customerDto.getHeight() - AGE_COEFFICIENT * customerDto.getAge() - WOMEN_COEFFICIENT;
        } else {
            return WEIGHT_COEFFICIENT * customerDto.getWeight() + HEIGHT_COEFFICIENT * customerDto.getHeight() - AGE_COEFFICIENT * customerDto.getAge() - MEN_COEFFICIENT;
        }
    }

    public double getWeightLossCalories(CustomerDto customerDto) {
        return getWeightMaintainCalories(customerDto) * WEIGHT_LOSS_COEFFICIENT;
    }

    public double getWeightGainCalories(CustomerDto customerDto) {
        return getWeightMaintainCalories(customerDto) * WEIGHT_GAIN_COEFFICIENT;
    }

    public double getWeightMaintainCalories(CustomerDto customerDto) { //предусмотреть нулпоинтр

        if (Activity.LOW == customerDto.getActivity()) {
            return getBasicMetabolism(customerDto) * LOW_ACTIVITY_COEFFICIENT;
        } else if (Activity.MEDIUM == customerDto.getActivity()) {
            return getBasicMetabolism(customerDto) * MEDIUM_ACTIVITY_COEFFICIENT;
        } else {
            return getBasicMetabolism(customerDto) * HIGH_ACTIVITY_COEFFICIENT;
        }
    }
}
