package com.zavadski.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;
}
