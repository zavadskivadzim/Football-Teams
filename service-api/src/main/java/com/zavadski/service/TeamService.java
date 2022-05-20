package com.zavadski.service;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team findTeamById(Integer id);

    Integer createTeam(Team team);

    Integer updateTeam(Team team);

    void deleteTeam(Integer teamId);

    Long count();

    boolean isTeamWithPlayers(Integer teamId);

    boolean isTeamUnique(String teamName);

}
