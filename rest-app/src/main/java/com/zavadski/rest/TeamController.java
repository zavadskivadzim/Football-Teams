package com.zavadski.rest;

import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;

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
    public final Integer updateTeam(@RequestBody @Valid Team team, BindingResult result) {

        if (result.hasErrors()) {
            throw new UnacceptableName(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        } else {
            Team newTeam = new Team();
            newTeam.setTeamName(team.getTeamName());
            if (this.teamService.isTeamUnique(newTeam.getTeamName())
                    || (Objects.equals(this.teamService.findTeamById(team.getTeamId()).getTeamName(), newTeam.getTeamName()))) {
                return teamService.updateTeam(team);
            } else {
                throw new UnacceptableName("Team with name " + team.getTeamName() + " already exists.");
            }
        }
    }

    @DeleteMapping(value = "/teams/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final void deleteTeamById(@PathVariable Integer id) {

        teamService.deleteTeam(id);
    }

    @GetMapping(value = "/teams/count")
    public final Long count() {

        return teamService.count();
    }

    @GetMapping("/teams/check/{id}")
    public boolean isTeamWithPlayers(@PathVariable Integer id) {
        return teamService.isTeamWithPlayers(id);
    }

    @GetMapping("/teams/unique/{teamName}")
    public boolean isTeamUnique(@PathVariable String teamName) {
        return teamService.isTeamUnique(teamName);
    }

}
