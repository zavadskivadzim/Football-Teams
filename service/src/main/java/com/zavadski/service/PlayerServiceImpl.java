package com.zavadski.service;

import com.zavadski.dao.PlayerDao;
import com.zavadski.model.Player;
import com.zavadski.service.api.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    @Autowired
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.findAll();
    }
}
