package com.zavadski.service.api;

import com.zavadski.model.dto.TeamWithPlayerDto;

import java.util.Collection;

public interface TeamWithPlayerDtoService {

    Collection<TeamWithPlayerDto> findAllTeamsWithNumberOfPlayers();

}
