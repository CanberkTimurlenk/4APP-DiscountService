package com.robotdreams.discountservice;

import com.robotdreams.discountservice.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DiscountServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(DiscountServiceApplication.class, args);

        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES", "ROLE_ADMIN");

        JwtUtil.generateToken(claims, "denemeName");

    }

}
