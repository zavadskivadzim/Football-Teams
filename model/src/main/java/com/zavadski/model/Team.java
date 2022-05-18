package com.zavadski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(max = 50, message = "Team name length should be less then 50")
    private String teamName;

    public Team(Integer teamId) {
        this.teamId = teamId;
    }
}
