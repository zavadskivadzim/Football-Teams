package com.zavadski.mongo.document;

import com.zavadski.model.Player;
import com.zavadski.mongo.model.PlayerMongo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "football-teams")
public class PlayersByAge {
    @Id
    private String id;
    private String dateOfCreating;
    private String age = "under 18";
    private String teamName;
    private Collection<PlayerMongo> player;

    public PlayersByAge(String dateOfCreating, String age, String teamName, Collection<PlayerMongo> player) {
        this.dateOfCreating = dateOfCreating;
        this.age = age;
        this.teamName = teamName;
        this.player = player;
    }
}
