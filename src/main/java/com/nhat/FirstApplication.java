package com.nhat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nhat.trading.infrastructure.secondary")
public class FirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class, args);
    }
}
