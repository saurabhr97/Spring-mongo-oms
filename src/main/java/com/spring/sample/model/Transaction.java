package com.spring.sample.model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;

import lombok.Data;

@Data
public class Transaction {
    private String id;
    private String customerId;
    private LocalDateTime transactionDate;
    private List<String> productIds;
    private Double price;

    public Transaction() {
    }

    
    public Transaction(String customerId, LocalDateTime transactionDate, List<String> productIds, Double price) {
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.productIds = productIds;
        this.price = price;
    }


    public Transaction(String id, String customerId, LocalDateTime transactionDate, List<String> productIds, Double price) {
        this.id = id;
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.productIds = productIds;
        this.price = price;
    }

    public Document toDocument() {
        Document document = new Document();
        if (this.id != null) {
            document.append("_id", this.id);
        }
        document.append("customerId", this.customerId);
        document.append("transactionDate", this.transactionDate);
        document.append("productIds", this.productIds);
        document.append("price", this.price);
        return document;
    }
}
