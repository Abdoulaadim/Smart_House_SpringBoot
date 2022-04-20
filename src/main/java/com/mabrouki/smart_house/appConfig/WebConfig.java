package com.mabrouki.smart_house.appConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//configuration global CORS

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public  void addCorsMappings(CorsRegistry registry) {
        registry
                //.addMapping("/user")
                .addMapping("/**")  //tous les routes
                //.allowedMethods("GET", "POST", "PUT")
                .allowedMethods("*") //tous les methodes
                //.allowedOrigins("http://localhost:4200");
                .allowedOrigins("*");
    }

}
