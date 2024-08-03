package com.spring.sample.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.MongoCursor;
import com.spring.sample.model.Customer;
import com.spring.sample.model.Product;
import com.spring.sample.repository.MongoRepository;

public class DatabaseHelper {

    @Autowired
    private MongoRepository mongoRepository;

    private final String productsCollection;
    private final String customersCollection;

    public DatabaseHelper(String productsCollection, String customersCollection) {
        this.productsCollection = productsCollection;
        this.customersCollection = customersCollection;
    }

    public void addProduct(Product product) {
        mongoRepository.addDocument(this.productsCollection, product.toDocument());
    }

    public void addCustomer(Customer customer) {
        mongoRepository.addDocument(this.customersCollection, customer.toDocument());
    }

    public List<Product> findProductByCategory(String productCategory) {
        MongoCursor<Document> cursor = mongoRepository.findDocuments(this.productsCollection, new Document("category", productCategory));
        List<Product> products = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                products.add(new Product(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return products;
    }

    public Product getProductbyId(String id) {
        return new Product(mongoRepository.findSingleDocument(this.productsCollection, new Document("_id", id)));
    }
}
