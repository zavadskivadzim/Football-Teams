package com.zavadski.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Player {

    private Integer playerId;

    private String firstName;

    private String surname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Integer teamId;

}
