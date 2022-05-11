package com.zavadski.service.impl;

import com.zavadski.dao.api.TeamDtoDao;
import com.zavadski.model.dto.TeamWithPlayerDto;
import com.zavadski.service.TeamWithPlayerDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamWithPlayerDtoServiceImpl implements TeamWithPlayerDtoService {

    private final TeamDtoDao teamDtoDao;

    public TeamWithPlayerDtoServiceImpl(TeamDtoDao teamDtoDao) {
        this.teamDtoDao = teamDtoDao;
    }

    @Override
    public List<TeamWithPlayerDto> findAllTeamsWithNumberOfPlayers() {
        return teamDtoDao.findAllTeamsWithNumberOfPlayers();
    }
}
