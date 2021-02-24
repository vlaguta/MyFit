package com.diplom.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTaskService {

    private final DailyMenuService dailyMenuService;

    public ScheduleTaskService(DailyMenuService dailyMenuService) {
        this.dailyMenuService = dailyMenuService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void createDailyMenuForAllCustomersTask(){
        dailyMenuService.saveDailyMenuForEveryCustomer();
    }
}
