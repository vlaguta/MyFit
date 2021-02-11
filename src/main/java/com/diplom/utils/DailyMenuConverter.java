package com.diplom.utils;

import com.diplom.dto.DailyMenuDto;
import com.diplom.model.DailyMenu;

public class DailyMenuConverter {

    public static DailyMenuDto convertDailyMenuEntityToDailyMenuDto(DailyMenu dailyMenu){

        return DailyMenuDto.builder()
                .products(dailyMenu.getProducts())
                .createdDate(dailyMenu.getCreatedDate())
                .build();
    }

    public static DailyMenu convertDailyMenuDtoToDailyMenuEntity(DailyMenuDto dailyMenuDto){

        return DailyMenu.builder()
                .products(dailyMenuDto.getProducts())
                .createdDate(dailyMenuDto.getCreatedDate())
                .build();
    }
}
