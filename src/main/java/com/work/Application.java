package com.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT ", "DELETE");
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
