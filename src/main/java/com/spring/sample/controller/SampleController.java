package com.spring.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sample.model.Customer;
import com.spring.sample.model.Product;
import com.spring.sample.util.DatabaseHelper;

@RestController
@RequestMapping("/api")
public class SampleController {
    
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private DatabaseHelper databaseHelper;

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("Hello, World!");
        return "Hello, World!";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product newProduct) {
        logger.info("Adding Product: {}", newProduct.getName());
        databaseHelper.addProduct(newProduct);
        return "Product Added: " + newProduct.getName();
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer) {
        logger.info("Adding Customer: {}", customer.getName());
        databaseHelper.addCustomer(customer);
        return "Customer Added: " + customer.getName();
    }

    @GetMapping("/findProductByCategory")
    public List<Product> findProductByCategory(@RequestParam(name = "productCategory") String productCategory) {
        logger.info("Searching for category: {}", productCategory);
        return databaseHelper.findProductByCategory(productCategory);
    }

    @GetMapping("/generateInvoice")
    public String generateInvoice(@RequestParam(name = "customerId") String customerId, @RequestParam(name = "productIds") List<String> productIds) {
        logger.info("Generating Invoice for Customer: {} and ProductIds: {}", customerId, productIds);
        databaseHelper.addTransaction(customerId, productIds);
        return "Invoice Generated for Customer: " + customerId;
    }
}