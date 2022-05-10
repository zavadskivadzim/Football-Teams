package com.zavadski.dao;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final Logger logger = LogManager.getLogger(PlayerDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public PlayerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Player> findAll() {

        logger.debug("find All Players");

        return entityManager.createQuery("from Player", Player.class).getResultList();
    }

    @Override
    public Player findById(Integer id) {

        logger.debug("Find Player by id={}", id);

        return entityManager.find(Player.class, id);
    }

    @Override
    @Transactional
    public Integer save(Player player) {

        logger.info("Create Player {}", player);

        entityManager.persist(player);

        return player.getPlayerId();
    }

    @Override
    @Transactional
    public Integer update(Player player) {

        logger.info("update player {}", player);

        entityManager.merge(player);

        return player.getPlayerId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        logger.info("delete player by id={}", id);

        Player player = entityManager.find(Player.class, id);
        entityManager.remove(player);
    }
}
