package com.zavadski.service.api;

import com.zavadski.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team findTeamById(Integer id);

    Integer createTeam(Team team);

    Integer updateTeam(Team team);

    void deleteTeam(Integer teamId);

}
