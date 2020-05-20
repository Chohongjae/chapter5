package com.chohongjae.chapter5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter5Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Chapter5Application.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
//        SpringApplication.run(Chapter5Application.class, args);
    }

}
