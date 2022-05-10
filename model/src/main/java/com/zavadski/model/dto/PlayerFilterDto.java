package com.zavadski.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PlayerFilterDto {

    private Integer playerId;

    private String firstName;

    private String surname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+3")
    private LocalDate birthday;

    private Integer teamId;

}
