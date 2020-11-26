package com.diplom.service;

import com.diplom.model.DailyMenu;
import com.diplom.model.Product;
import com.diplom.repository.DailyMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyMenuService {
     private final DailyMenuRepository dailyMenuRepository;

    public DailyMenu getDailyMenu(int id){
        return dailyMenuRepository.getOne(id);
    }

    public void saveDailyMenu(DailyMenu dailyMenu){
        dailyMenuRepository.save(dailyMenu);
    }

    public void deleteDailyMenu(DailyMenu dailyMenu){
        dailyMenuRepository.delete(dailyMenu);
    }
}
