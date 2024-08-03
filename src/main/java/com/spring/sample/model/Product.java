package com.spring.sample.model;

import org.bson.Document;

import lombok.Data;

@Data
public class Product {
    private String name;
    private Double price;
    private String description;
    private String category;
    private String id;

    public Product() {
        // add a default constructor here
    }

    public Product(String name, Double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.id = null;
    }

    public Product(String name, Double price, String description, String category, String id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product(Document document) {
        this.id = document.get("_id").toString();
        this.name = document.get("name").toString();
        this.price = (Double) document.get("price");
        this.description = document.get("description").toString();
        this.category = document.get("category").toString();
    }

    public Document toDocument() {
        Document document = new Document();
        if (this.id != null) {
            document.append("_id", this.id);
        }
        document.append("name", this.name);
        document.append("price", this.price);
        document.append("description", this.description);
        document.append("category", this.category);
        return document;
    }
}
