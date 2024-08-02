package com.spring.sample.model;

import org.bson.Document;

import lombok.Data;

@Data
public class Product {
    private String name;
    private Double price;
    private String description;
    private String category;

    public Product() {
        // add a default constructor here
    }

    // @JsonCreator(mode = Creator.Mode.DEFAULT)
    public Product(String name, Double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("name", this.name);
        document.append("price", this.price);
        document.append("description", this.description);
        document.append("category", this.category);
        return document;
    }
}
