package com.zavadski.rest;

import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/players")
    public final ResponseEntity<List<Player>> getAllPlayers() {

        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(value = "/players/{id}")
    public final ResponseEntity<Player> findPlayerById(@PathVariable Integer id) {

        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping(value = "/players")
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
    public final ResponseEntity<Integer> deletePlayerById(@PathVariable Integer id) {

        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
