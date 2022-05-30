package com.zavadski.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.zavadski.model.constants.Constants.PLAYER_NAME_SIZE;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column
    @NotBlank(message = "Player name can not be empty")
    @Size(max = PLAYER_NAME_SIZE, message = "Player name must be less then {max} characters long")
    private String firstName;

    @Column
    @NotBlank(message = "Player surname can not be empty")
    private String surname;

    @Column
    @Past(message = "Incorrect date of birthday")
    @NotNull(message = "Birthday can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+3")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
