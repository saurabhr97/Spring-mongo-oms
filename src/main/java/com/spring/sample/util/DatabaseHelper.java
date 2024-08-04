package com.spring.sample.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.in;
import com.spring.sample.model.Customer;
import com.spring.sample.model.Product;
import com.spring.sample.model.Transaction;
import com.spring.sample.repository.MongoRepository;
import static com.spring.sample.util.DatabaseConstants.CUSTOMERS_COLLECTION;
import static com.spring.sample.util.DatabaseConstants.PRODUCTS_COLLECTION;
import static com.spring.sample.util.DatabaseConstants.TRANSACTIONS_COLLECTION;

public class DatabaseHelper {

    @Autowired
    private MongoRepository mongoRepository;

    public DatabaseHelper() {
    }

    public void addProduct(Product product) {
        mongoRepository.addDocument(PRODUCTS_COLLECTION, product.toDocument());
    }

    public void addCustomer(Customer customer) {
        mongoRepository.addDocument(CUSTOMERS_COLLECTION, customer.toDocument());
    }

    public void addTransaction(String customerId, List<String> productIds) {
        List<Product> products = findProductByIdList(productIds);
        Double price = products.stream().mapToDouble(Product::getPrice).sum();
        Transaction transaction = new Transaction(customerId, LocalDateTime.now(ZoneOffset.UTC), productIds, price);
        mongoRepository.addDocument(TRANSACTIONS_COLLECTION, transaction.toDocument());
    }

    public List<Product> findProductByCategory(String productCategory) {
        MongoCursor<Document> cursor = mongoRepository.findDocuments(PRODUCTS_COLLECTION, new Document("category", productCategory));
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

    public List<Product> findProductByIdList(List<String> productIds) {
        List<ObjectId> objectIdList = productIds.stream().map(ObjectId::new).toList();
        Bson findProducts = in("_id", objectIdList);
        MongoCursor<Document> cursor = mongoRepository.findDocuments(PRODUCTS_COLLECTION, findProducts);
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
        return new Product(mongoRepository.findSingleDocument(PRODUCTS_COLLECTION, new Document("_id", id)));
    }
}
