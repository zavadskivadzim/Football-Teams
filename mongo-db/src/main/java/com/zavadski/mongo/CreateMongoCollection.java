package com.zavadski.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.zavadski.service.PlayerService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class CreateMongoCollection {

    private final PlayerService playerService;

    @Autowired
    public CreateMongoCollection(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void createCollection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Football-Teams");
        database.getCollection("football-teams").drop();
        database.createCollection("football-teams");

        MongoCollection<Document> collection = database.getCollection("football-teams");

        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("date", LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, uuuu")))
                    .append("player", playerService.getAllPlayers().get(0))
            );
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }
}
