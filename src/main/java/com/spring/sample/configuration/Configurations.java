package com.spring.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.spring.sample.repository.MongoRepository;
import com.spring.sample.util.DatabaseHelper;

@SpringBootConfiguration
public class Configurations {

    @Bean
    public MongoRepository getMongoRepository(@Value("${mongodb.connectionString}") String connectionString) {
        return new MongoRepository(connectionString);
    }

    @Bean
    public DatabaseHelper getDatabaseHelper() {
        return new DatabaseHelper();
    }
}
