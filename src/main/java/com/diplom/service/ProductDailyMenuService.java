package com.diplom.service;

import com.diplom.dto.ProductDto;
import com.diplom.model.DailyMenu;
import com.diplom.model.ProductDailyMenu;
import com.diplom.repository.ProductDailyMenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.diplom.utils.ProductConverter.converterProductDtoToEntity;

@Service
public class ProductDailyMenuService {

    private final ProductDailyMenuRepository productDailyMenuRepository;

    public ProductDailyMenuService(ProductDailyMenuRepository productDailyMenuRepository) {
        this.productDailyMenuRepository = productDailyMenuRepository;
    }

    public List<ProductDailyMenu> get(int dailyId){
       return productDailyMenuRepository.findAllProductDailyMenuByDailyMenuId(dailyId);
    }

}
