package com.zavadski.rest;

import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Player;
import com.zavadski.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;

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
    public final Integer createPlayer(@RequestBody @Valid Player player, BindingResult result) {

        if (result.hasErrors()) {
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            return playerService.createPlayer(player);
        }
    }

    @PutMapping(value = "/players")
    public final Integer updateTeam(@RequestBody @Valid Player player, BindingResult result) {

        if (result.hasErrors()) {
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            return playerService.updatePlayer(player);
        }
    }

    @DeleteMapping(value = "/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final void deletePlayerById(@PathVariable Integer id) {

        playerService.deletePlayer(id);
    }

}
