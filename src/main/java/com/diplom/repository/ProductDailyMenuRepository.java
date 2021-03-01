package com.diplom.repository;

import com.diplom.enums.Eating;
import com.diplom.model.ProductDailyMenu;
import com.diplom.service.ProductDailyMenuService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDailyMenuRepository extends JpaRepository<ProductDailyMenu, Integer> {

    List<ProductDailyMenu> findAllProductDailyMenuByDailyMenuId(int daily);

    ProductDailyMenu findByDailyMenuIdAndEatingAndProductId(int daily, Eating eating, int productId);


}
