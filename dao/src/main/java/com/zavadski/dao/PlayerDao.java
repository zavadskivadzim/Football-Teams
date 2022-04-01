package com.zavadski.dao;

import com.zavadski.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDao extends JpaRepository<Player, Integer> {
}
