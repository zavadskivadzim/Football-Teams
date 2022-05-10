package com.zavadski.dao.api;

import com.zavadski.model.dto.TeamWithPlayerDto;

import java.util.List;

public interface TeamDtoDao {

    List<TeamWithPlayerDto> findAllTeamsWithNumberOfPlayers();

}
