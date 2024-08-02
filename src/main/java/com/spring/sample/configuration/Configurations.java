package com.spring.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.spring.sample.repository.MongoRepository;
import com.spring.sample.services.ManageProductsService;

@SpringBootConfiguration
public class Configurations {

    @Value("${mongodb.connectionString}")
    private String connectionString;
    @Bean
    public MongoRepository getMongoRepository() {
        return new MongoRepository(this.connectionString);
    }

    @Bean
    public ManageProductsService getManageProductsService() {
        return new ManageProductsService("products");
    }
}
