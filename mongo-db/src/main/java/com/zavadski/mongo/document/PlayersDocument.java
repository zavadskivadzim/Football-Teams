package com.zavadski.mongo.document;

import com.zavadski.mongo.model.PlayersByAgeAndTeam;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection = "football-teams")
public class PlayersDocument {

    @Id
    private String id;
    private String dateOfCreating;
    private List<PlayersByAgeAndTeam> playersByAgeAndTeams;

    public PlayersDocument(String dateOfCreating, List<PlayersByAgeAndTeam> playersByAgeAndTeams) {
        this.dateOfCreating = dateOfCreating;
        this.playersByAgeAndTeams = playersByAgeAndTeams;
    }
}
