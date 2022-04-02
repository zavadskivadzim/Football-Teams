package com.zavadski.dao;

import com.zavadski.dao.api.PlayerDao;
import com.zavadski.model.Player;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final EntityManager entityManager;

    @Autowired
    public PlayerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Player> getAllPlayer() {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Player", Player.class);
        List<Player> players = query.getResultList();

        return players;
    }
}
