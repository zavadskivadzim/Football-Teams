package com.zavadski.dao.api;

import com.zavadski.model.Player;
import com.zavadski.model.Team;

import java.util.List;

public interface TeamDao {

    List<Team> findAll();

    Team findById(Integer id);

    Integer save(Team team);

    Integer update(Team team);

    void delete(Integer teamId);

}
