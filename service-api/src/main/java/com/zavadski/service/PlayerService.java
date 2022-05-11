package com.zavadski.service;

import com.zavadski.model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player findPlayerById(Integer id);

    Integer createPlayer(Player player);

    Integer updatePlayer(Player player);

    void deletePlayer(Integer playerId);

}
