package com.spring.sample.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.MongoCursor;
import com.spring.sample.model.Product;
import com.spring.sample.repository.MongoRepository;

public class ManageProductsService {

    @Autowired
    private MongoRepository mongoRepository;

    private final String mongoCollectionName;

    public ManageProductsService(String productsCollectionName) {
        this.mongoCollectionName = productsCollectionName;
    }

    public void addProduct(Product product) {
        mongoRepository.addDocument(this.mongoCollectionName, product.toDocument());
    }

    public List<Product> findProductByName(String productName) {
        MongoCursor<Document> cursor = mongoRepository.findDocuments(this.mongoCollectionName, new Document("name", productName));
        List<Product> products = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                Document document = cursor.next();
                Product product = new Product(document.get("name").toString(), (Double) document.get("price"), document.get("description").toString(), document.get("category").toString());
                products.add(product);
            }
        } finally {
            cursor.close();
        }
        return products;
    }
}
