package com.zavadski.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import com.zavadski.service.PlayerService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        MongoDatabase db = mongoClient.getDatabase("Football-Teams");
        db.getCollection("football-teams").drop();
        MongoCollection<Document> coll = db.getCollection("football-teams");

        List<Document> documents = new ArrayList<>();
        Document doc1 = new Document("date", LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, uuuu")))
                .append("player", playerService.getAllPlayers().get(0).getFirstName());
        documents.add(doc1);
        InsertManyResult result = coll.insertMany(documents);
    }
}
