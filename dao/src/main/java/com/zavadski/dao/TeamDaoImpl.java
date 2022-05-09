package com.zavadski.dao;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    @Transactional
    public List<Team> findAll() {

        logger.debug("find All Teams");

        return entityManager.createQuery("from Team", Team.class).getResultList();
    }

    @Override
    @Transactional
    public Team findById(Integer id) {

        logger.debug("Find team by id={}", id);

        return entityManager.find(Team.class, id);
    }

    @Override
    @Transactional
    public Integer save(Team team) {

        logger.info("Create team {}", team);

        entityManager.persist(team);

        return team.getTeamId();
    }

    @Override
    @Transactional
    public Integer update(Team team) {

        logger.info("update team {}", team);

        entityManager.merge(team);

        return team.getTeamId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        logger.info("delete team by id={}", id);

        Team team = entityManager.find(Team.class, id);
        entityManager.remove(team);
    }

}
