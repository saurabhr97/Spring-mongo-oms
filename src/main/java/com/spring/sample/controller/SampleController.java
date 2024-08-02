package com.spring.sample.controller;

import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sample.model.Product;
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

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product newProduct) {
        logger.info("Adding Product: {}", newProduct.getName());
        manageProductsService.addProduct(newProduct);
        return "Product Added: " + newProduct.getName();
    }

    @GetMapping("/findProductByName")
    public Document findProductByName(@RequestParam(name = "productName") String productName) {
        logger.info("Searching for product: {}", productName);
        List<Product> products = manageProductsService.findProductByName(productName);
        return products.get(0).toDocument();
    }
}