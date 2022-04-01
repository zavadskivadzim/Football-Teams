package com.zavadski.dao;

import com.zavadski.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team, Integer> {
}
