package com.zavadski.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.zavadski.model.constants.Constants.PLAYER_NAME_SIZE;
import static com.zavadski.model.constants.Constants.TEAM_NAME_SIZE;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column
    @NotBlank(message = "Player name can't be empty")
    @Size(max = PLAYER_NAME_SIZE, message = "Player name must be less then {max} characters long")
    private String firstName;

    @Column
    @NotBlank(message = "Player surname can't be empty")
    private String surname;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+3")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
