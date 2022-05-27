package com.zavadski.dao;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
public class TeamDaoImpl implements TeamDao {

    private final Logger logger = LogManager.getLogger(TeamDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public TeamDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Team> findAll() {

        logger.debug("find All Teams");

        return entityManager.createQuery("from Team", Team.class).getResultList();
    }

    @Override
    public Team findById(Integer id) {

        logger.debug("Find team by id={}", id);

        return entityManager.find(Team.class, id);
    }

    @Override
    @Transactional
    public Integer save(Team team) {

        logger.info("Create team {}", team);

        if (!isTeamUnique(team.getTeamName())) {
            logger.warn("Team with the same name {} already exists.", team.getTeamName());
            throw new UnacceptableName("Team with the same name already exists in DB.");
        }

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

    @Override
    public Long count() {

        logger.info("count teams");

        return (Long) entityManager.createQuery("select count(*) from Team").getResultList().get(0);
    }

    @Override
    public boolean isTeamWithPlayers(Integer teamId) {

        return getPlayersCountForTeam(teamId) > 0;
    }

    private Integer getPlayersCountForTeam(Integer id) {

        TypedQuery<Long> query = entityManager.createQuery("select count(*) from Player where team.id = :id", Long.class);
        query.setParameter("id", id);
        Long result = query.getResultList().get(0);

        return result.intValue();
    }

    @Override
    public boolean isTeamUnique(String teamName) {

        logger.debug("Check TeamName: {} on unique", teamName);

        TypedQuery<Long> query = entityManager.createQuery("select count(team_name) from Team where lower(team_name) = lower(:teamName)", Long.class);
        query.setParameter("teamName", teamName);
        Long result = query.getResultList().get(0);

        return result == 0;
    }

}
