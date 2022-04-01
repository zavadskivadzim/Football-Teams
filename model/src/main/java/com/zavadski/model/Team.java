package com.zavadski.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Team {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Column
    private String teamName;
}
