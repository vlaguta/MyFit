package com.diplom.repository;

import com.diplom.model.Customer;
import com.diplom.model.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    void deleteById(int id);

    //DailyMenu findById(int id);

    //Optional<DailyMenu> findByCustomerAndCreatedDate(Customer customer, LocalDate createdDate);
    Optional<DailyMenu> findByCustomerId(int customerId/*, LocalDate createdDate*/);
    //DailyMenu findByCustomerId(int customerId/*, LocalDate localDate*/);
    //DailyMenu findByCustomer(Customer customer/*, LocalDate localDate*/);


}
