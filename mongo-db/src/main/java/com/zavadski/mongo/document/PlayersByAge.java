package com.zavadski.mongo.document;

import com.zavadski.mongo.model.TeamMongo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection = "football-teams")
public class PlayersByAge {

    @Id
    private String id;
    private String dateOfCreating;
    private String age;
    private List<TeamMongo> teams;

    public PlayersByAge(String dateOfCreating, String age, List<TeamMongo> teams) {
        this.dateOfCreating = dateOfCreating;
        this.age = age;
        this.teams = teams;
    }
}
