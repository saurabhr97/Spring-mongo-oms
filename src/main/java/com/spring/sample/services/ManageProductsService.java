package com.spring.sample.services;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.sample.repository.MongoRepository;

public class ManageProductsService {

    @Autowired
    private MongoRepository mongoRepository;

    private final String mongoCollectionName;

    public ManageProductsService(String productsCollectionName) {
        this.mongoCollectionName = productsCollectionName;
    }

    public void addProduct(String productName) {
        Document productDocument = new Document("name", productName);
        mongoRepository.addDocument(this.mongoCollectionName, productDocument);
    }
}
