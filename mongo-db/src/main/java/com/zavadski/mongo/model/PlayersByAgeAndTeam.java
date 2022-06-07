package com.zavadski.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayersByAgeAndTeam {

    private String age;
    private List<TeamMongo> teams;

}
