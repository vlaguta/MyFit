package com.diplom.repository;

import com.diplom.model.Customer;
import com.diplom.model.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    void deleteById(int id);

    //DailyMenu findById(int id);

    Optional<DailyMenu> findByCustomerLoginAndCreatedDate(String login, LocalDate createdDate);
    Optional<DailyMenu> findByCustomerLogin(String login);
    Optional<DailyMenu> findByCustomerIdAndCreatedDate(int id, LocalDate createdDate);
    Optional<DailyMenu> findByCustomerId(int customerId/*, LocalDate createdDate*/);


    //List<DailyMenu>findAll();
    //DailyMenu findByCustomerId(int customerId/*, LocalDate localDate*/);
    //DailyMenu findByCustomer(Customer customer/*, LocalDate localDate*/);


}
