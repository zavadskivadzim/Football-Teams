package com.zavadski.rest;

import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(value = "/teams")
    public final ResponseEntity<List<Team>> getAllTeams() {

        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(value = "/teams/{id}")
    public final ResponseEntity<Team> findTeamById(@PathVariable Integer id) {

        Team team = teamService.findTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
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
    public final ResponseEntity<Integer> deleteTeamById(@PathVariable Integer id) {

        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/teams/count")
    public final Long count() {

        return teamService.count();
    }

    @GetMapping("/teams/check/{id}")
    public boolean checkTeamWithPlayers(@PathVariable Integer id) {
        return teamService.checkOnTeamWithPlayers(id);
    }

    @GetMapping("/teams/unique/{teamName}")
    public boolean checkTeamOnUnique(@PathVariable String teamName) {
        return teamService.checkTeamOnUnique(teamName);
    }

}
