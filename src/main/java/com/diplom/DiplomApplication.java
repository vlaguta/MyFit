package com.diplom;

import com.diplom.repository.ProductRepository;
import com.diplom.service.PhotoService;
import com.diplom.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class DiplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);

    }
        //@Bean
        //CommandLineRunner init(PhotoService photoService) {
        //    return (args) -> {
        //        photoService.deleteAll();
        //        photoService.init();
        //    };

    }
//}
