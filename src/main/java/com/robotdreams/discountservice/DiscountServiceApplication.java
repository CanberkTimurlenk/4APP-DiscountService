package com.robotdreams.discountservice;

import com.robotdreams.discountservice.service.JwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.robotdreams.discountservice.repository")
public class DiscountServiceApplication {


    public static void main(String[] args) {

        SpringApplication.run(DiscountServiceApplication.class, args);

        JwtService jwtService = new JwtService();

        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES", "ROLE_ADMIN");


        var asd = jwtService.generateToken(claims, "denemeName");
        System.out.println(asd);
    }

}
