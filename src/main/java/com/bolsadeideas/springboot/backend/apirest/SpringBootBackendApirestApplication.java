package com.bolsadeideas.springboot.backend.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootBackendApirestApplication implements CommandLineRunner {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBackendApirestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = "12345";

        Stream.of(1,2,3,4)
                .forEach(x->{
                    String passwordE = passwordEncoder.encode(password);
                    System.out.println(passwordE);
                });
    }
}

