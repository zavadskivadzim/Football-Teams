package com.zavadski.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamWithPlayerDto {

    private Integer teamId;

    private String teamName;

    private Long numberOfPlayers;

}
