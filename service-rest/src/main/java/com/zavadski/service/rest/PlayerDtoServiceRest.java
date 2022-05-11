package com.zavadski.service.rest;

import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerFilterDtoService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerDtoServiceRest implements PlayerFilterDtoService {

    private String url;

    private RestTemplate restTemplate;

    public PlayerDtoServiceRest() {
    }

    public PlayerDtoServiceRest(String url, RestTemplate restTemplate) {
        this();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PlayerDto> filterPlayersByBirthday(LocalDate startDate, LocalDate endDate) {

        ResponseEntity<List<PlayerDto>> responseEntity = restTemplate.exchange(
                url + getDatesAsParameter(startDate, endDate),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return responseEntity.getBody();
    }

    private String getDatesAsParameter(LocalDate startDate, LocalDate endDate) {

        StringBuilder params = new StringBuilder("?startDate=");
        if (startDate != null) {
            params.append(startDate);
        }
        params.append("&endDate=");
        if (endDate != null) {
            params.append(endDate);
        }
        return params.toString();
    }
}
