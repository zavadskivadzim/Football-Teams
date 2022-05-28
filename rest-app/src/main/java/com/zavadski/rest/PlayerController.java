package com.zavadski.rest;

import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/players")
    public final Collection<Player> getAllPlayers() {

        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/players/{id}")
    public final Player findPlayerById(@PathVariable Integer id) {

        return playerService.findPlayerById(id);
    }

    @PostMapping(value = "/players")
    @ResponseStatus(HttpStatus.CREATED)
    public final ResponseEntity<Integer> createPlayer(@RequestBody Player player) {

        Integer id = playerService.createPlayer(player);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(value = "/players")
    public final ResponseEntity<Integer> updateTeam(@RequestBody Player player) {

        Integer id = playerService.updatePlayer(player);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{id}")
    public final void deletePlayerById(@PathVariable Integer id) {

        playerService.deletePlayer(id);
    }

}
