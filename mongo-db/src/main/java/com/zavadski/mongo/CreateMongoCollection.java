package com.zavadski.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zavadski.mongo.document.PlayersByAge;
import com.zavadski.mongo.model.PlayerMongo;
import com.zavadski.mongo.model.TeamMongo;
import com.zavadski.mongo.repository.PlayersByAgeRepository;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CreateMongoCollection {

    private final PlayerService playerService;
    private final TeamService teamService;
    private PlayersByAgeRepository repository;

    @Autowired
    public CreateMongoCollection(PlayerService playerService, TeamService teamService, PlayersByAgeRepository repository) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.repository = repository;
    }

    public void createCollection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoDatabase db = mongoClient.getDatabase("Football-Teams").withCodecRegistry(pojoCodecRegistry);
        db.getCollection("football-teams").drop();
        MongoCollection<Document> coll = db.getCollection("football-teams");

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, uuuu"));

        PlayersByAge playersByAge = new PlayersByAge(
                currentDate,
                "under 18",
                (teamService.getAllTeams().stream()
                        .map(TeamMongo::fromTeam)
                        .collect(Collectors.toList())
                        .stream().peek(teamMongo -> teamMongo.setPlayers(playerService.getAllPlayers().stream()
                                .filter(playerMongo -> Objects.equals(playerMongo.getTeam().getTeamName(), teamMongo.getTeamName()))
                                .map(PlayerMongo::fromPlayer)
                                .filter(playerMongo -> playerMongo.getAge() < 18)
                                .collect(Collectors.toList()))
                        )
                        .collect(Collectors.toList())
                        .stream().filter(teamMongo -> !teamMongo.getPlayers().isEmpty()).collect(Collectors.toList())
                )
        );

        repository.insert(playersByAge);

    }
}
