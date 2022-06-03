package com.zavadski.mongo.model;

import com.zavadski.model.Player;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class PlayerMongo {

    private String firstName;
    private Long age;

    public static PlayerMongo fromPlayer(Player player) {
        PlayerMongo playerMongo = new PlayerMongo();
        playerMongo.setFirstName(player.getFirstName() + player.getSurname());
        playerMongo.setAge(player.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS));
        return playerMongo;
    }
}
