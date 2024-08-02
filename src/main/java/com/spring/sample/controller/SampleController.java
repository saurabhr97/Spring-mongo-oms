package com.spring.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sample.services.ManageProductsService;

@RestController
@RequestMapping("/api")
public class SampleController {
    
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private ManageProductsService manageProductsService;

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("Hello, World!");
        return "Hello, World!";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("productName") String productName) {
        logger.info("Adding Product: {}", productName);
        manageProductsService.addProduct(productName);
        return "Product Added: " + productName;
    }
}