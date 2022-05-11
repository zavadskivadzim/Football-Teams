package com.zavadski.service.rest;

import com.zavadski.model.dto.TeamWithPlayerDto;
import com.zavadski.service.TeamWithPlayerDtoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TeamDtoServiceRest implements TeamWithPlayerDtoService {

    private String url;

    private RestTemplate restTemplate;

    public TeamDtoServiceRest() {

    }

    public TeamDtoServiceRest(String url, RestTemplate restTemplate) {
        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TeamWithPlayerDto> findAllTeamsWithNumberOfPlayers() {

        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<TeamWithPlayerDto>) responseEntity.getBody();
    }
}
