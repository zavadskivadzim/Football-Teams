package com.zavadski.rest;

import com.zavadski.mongo.CreateMongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MongoController {

    private final CreateMongoCollection createMongoCollection;

    @Autowired
    public MongoController(CreateMongoCollection createMongoCollection) {
        this.createMongoCollection = createMongoCollection;
    }

    @GetMapping(value = "/write_to_mongo")
    public final void writeToMongo() {

        createMongoCollection.createCollection();
    }
}
