package com.spring.sample.repository;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoRepository {
    private static final Logger logger = LoggerFactory.getLogger(MongoRepository.class);
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public MongoRepository(String connectionString) {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        this.mongoClient = MongoClients.create(settings);
        this.database = this.mongoClient.getDatabase("OMS");
        database.runCommand(new Document("ping", 1));
        logger.info("Pinged your deployment. You successfully connected to MongoDB!");
    }

    public void addDocument(String collectionName, Document document) {
        this.database.getCollection(collectionName).insertOne(document);
    }

    public MongoCursor<Document> findDocuments(String collectionName, Document query) {
        return this.database.getCollection(collectionName).find(query).iterator();
    }

    public Document findSingleDocument(String collectionName, Document query) {
        return this.database.getCollection(collectionName).find(query).first();
    }
}
