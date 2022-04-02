package com.zavadski.service;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.model.Team;
import com.zavadski.service.api.TeamService;
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
        return teamDao.getAllTeams();
    }
}
