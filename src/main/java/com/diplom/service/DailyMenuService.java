package com.diplom.service;

import com.diplom.dto.DailyMenuDto;
import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.repository.DailyMenuRepository;
import static com.diplom.utils.DailyMenuConverter.convertDailyMenuDtoToDailyMenuEntity;
import static com.diplom.utils.DailyMenuConverter.convertDailyMenuEntityToDailyMenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyMenuService {
     private final DailyMenuRepository dailyMenuRepository;

    public DailyMenuDto getDailyMenu(int id){
        return convertDailyMenuEntityToDailyMenuDto(dailyMenuRepository.getOne(id));
    }

    public void saveDailyMenu(DailyMenuDto dailyMenuDto){
        dailyMenuRepository.save(convertDailyMenuDtoToDailyMenuEntity(dailyMenuDto));
    }

    public void deleteDailyMenu(int id){
        dailyMenuRepository.deleteById(id);
    }

    public void updateDailyMenu(int id, List<Product> products){
        DailyMenu dailyMenu=dailyMenuRepository.getOne(id);
        dailyMenu.setProducts(products);
        dailyMenuRepository.save(dailyMenu);
    }
}
