package com.lh.caweb.nginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class CaWebNginxApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaWebNginxApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello nginx";
    }
}
