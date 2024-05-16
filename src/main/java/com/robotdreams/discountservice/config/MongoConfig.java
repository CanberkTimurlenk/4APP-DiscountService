package com.robotdreams.discountservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableMongoRepositories(basePackages = "com.robotdreams.discountservice.repository")
@EnableMongoAuditing
public class MongoConfig {



}
