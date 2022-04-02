package com.zavadski.dao;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TeamDaoImpl implements TeamDao {

    private final Logger logger = LogManager.getLogger(TeamDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public TeamDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Team> getAllTeams() {

        logger.debug("Get All Teams");

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Team", Team.class);
        List<Team> teams = query.getResultList();

        return teams;
    }
}
