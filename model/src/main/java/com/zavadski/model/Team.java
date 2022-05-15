package com.zavadski.model;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;

    public Team(Integer teamId) {
        this.teamId = teamId;
    }
}
