package com.example.estrougedemospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EstRougeDemoSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstRougeDemoSpringbootApplication.class, args);
    }

}
