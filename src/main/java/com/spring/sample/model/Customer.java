package com.spring.sample.model;

import org.bson.Document;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private String email;
    private String id;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email, String id) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(Document document) {
        this.id = document.get("_id").toString();
        this.name = document.get("name").toString();
        this.email = document.get("email").toString();
    }

    public Document toDocument() {
        Document document = new Document();
        if (this.id != null) {
            document.append("_id", this.id);
        }
        document.append("name", this.name);
        document.append("email", this.email);
        return document;
    }
}
