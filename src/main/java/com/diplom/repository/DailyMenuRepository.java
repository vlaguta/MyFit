package com.diplom.repository;

import com.diplom.model.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    public void deleteById(int id);

}
