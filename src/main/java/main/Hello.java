package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class Hello {
    public static void main(String[] args) {
        SpringApplication.run(Hello.class, args);
    }
}
