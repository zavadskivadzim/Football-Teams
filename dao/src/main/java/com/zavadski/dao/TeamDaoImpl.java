package com.zavadski.dao;

import com.zavadski.dao.api.TeamDao;
import com.zavadski.dao.exception.FieldNullPointerException;
import com.zavadski.dao.exception.UnacceptableName;
import com.zavadski.model.Team;
import com.zavadski.model.dto.PlayerDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.zavadski.model.constants.Constants.TEAM_NAME_SIZE;

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

        if (team.getTeamName().length() > TEAM_NAME_SIZE) {
            logger.warn("Team name {} is too long", team.getTeamName());
            throw new UnacceptableName("Team name length should be <=" + TEAM_NAME_SIZE);
        }

        if (team.getTeamName().isEmpty()) {
            logger.error("Not all fields are filled in Team");
            throw new FieldNullPointerException("Not all fields are filled in Team");
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

        //todo
        TypedQuery<Integer> query = entityManager.createQuery("select count(*) from Player where team = :id", Integer.class);
        query.setParameter("id", id);
        Long result = query.getResultList().get(0);

        return (Integer) Long.result;
    }

}
