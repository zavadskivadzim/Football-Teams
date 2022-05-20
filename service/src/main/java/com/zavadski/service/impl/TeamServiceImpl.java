package com.zavadski.service.impl;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;

    @Autowired
    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @Override
    public Team findTeamById(Integer id) {
        return teamDao.findById(id);
    }

    @Override
    public Integer createTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Integer updateTeam(Team team) {
        return teamDao.update(team);
    }

    @Override
    public void deleteTeam(Integer teamId) {
        teamDao.delete(teamId);
    }

    @Override
    public Long count() {
        return teamDao.count();
    }

    @Override
    public boolean isTeamWithPlayers(Integer teamId) {
        return teamDao.isTeamWithPlayers(teamId);
    }

    @Override
    public boolean isTeamUnique(String teamName) {
        return teamDao.isTeamUnique(teamName, 0);
    }

}
