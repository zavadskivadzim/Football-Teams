package com.zavadski.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.zavadski.model.constants.Constants.TEAM_NAME_SIZE;

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
    @NotBlank(message = "Team name can't be empty")
    @Size(max = TEAM_NAME_SIZE, message = "Team name must be less then {max} characters long")
    private String teamName;

    public Team(Integer teamId) {
        this.teamId = teamId;
    }
}
