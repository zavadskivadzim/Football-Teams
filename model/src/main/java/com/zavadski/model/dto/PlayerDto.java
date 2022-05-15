package com.zavadski.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zavadski.model.Team;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private Integer playerId;

    private String firstName;

    private String surname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+3")
    private LocalDate birthday;

    private Team team;

}
