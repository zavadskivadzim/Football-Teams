package com.zavadski.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column
    private String firstName;

    @Column
    private String surname;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}