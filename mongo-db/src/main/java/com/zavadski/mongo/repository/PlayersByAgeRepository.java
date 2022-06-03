package com.zavadski.mongo.repository;

import com.zavadski.mongo.document.PlayersByAge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersByAgeRepository
        extends MongoRepository<PlayersByAge, String> {
}

