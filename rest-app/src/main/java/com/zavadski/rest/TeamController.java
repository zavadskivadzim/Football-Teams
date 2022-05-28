package com.zavadski.rest;

import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/teams/{id}")
    public final Team findTeamById(@PathVariable Integer id) {

        return teamService.findTeamById(id);
    }

    @PostMapping(value = "/teams")
    public final ResponseEntity<Integer> createTeam(@RequestBody Team team) {

        Integer id = teamService.createTeam(team);
        return new ResponseEntity<>(id, HttpStatus.CREATED);

    }

    @PutMapping(value = "/teams")
    public final ResponseEntity<Integer> updateTeam(@RequestBody Team team) {

        Integer id = teamService.updateTeam(team);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/teams/{id}")
    public final void deleteTeamById(@PathVariable Integer id) {

        teamService.deleteTeam(id);
    }

    @GetMapping(value = "/teams/count")
    public final Long count() {

        return teamService.count();
    }

    @GetMapping("/teams/check/{id}")
    public boolean checkTeamWithPlayers(@PathVariable Integer id) {
        return teamService.isTeamWithPlayers(id);
    }

    @GetMapping("/teams/unique/{teamName}")
    public boolean checkTeamOnUnique(@PathVariable String teamName) {
        return teamService.isTeamUnique(teamName);
    }

}
