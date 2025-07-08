package com.istar.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IstarServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IstarServiceApplication.class, args);
    }
}
