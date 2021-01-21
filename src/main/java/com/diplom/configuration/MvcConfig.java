package com.diplom.configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/").setViewName("profile");
        registry.addViewController("/customers/login");
        registry.addViewController("/registration").setViewName("/security/registration");
        registry.addViewController("/login").setViewName("/security/login");
    }

}
