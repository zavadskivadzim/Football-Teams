package com.zavadski.rest;

import com.zavadski.model.Team;
import com.zavadski.service.api.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/teams")
    public final Collection<Team> getAllTeams() {

        return teamService.getAllTeams();
    }

}
