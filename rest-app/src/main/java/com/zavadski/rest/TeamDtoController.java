package com.zavadski.rest;

import com.zavadski.model.dto.TeamWithPlayerDto;
import com.zavadski.service.TeamWithPlayerDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TeamDtoController {

    private static final Logger logger = LogManager.getLogger(TeamDtoController.class);

    private final TeamWithPlayerDtoService teamWithPlayerDtoService;

    public TeamDtoController(TeamWithPlayerDtoService teamWithPlayerDtoService) {
        this.teamWithPlayerDtoService = teamWithPlayerDtoService;
    }

    @GetMapping(value = "team_with_players")
    public final Collection<TeamWithPlayerDto> TeamWithPlayers() {

        logger.debug(" find Teams With Players");

        return teamWithPlayerDtoService.findAllTeamsWithNumberOfPlayers();
    }
}
