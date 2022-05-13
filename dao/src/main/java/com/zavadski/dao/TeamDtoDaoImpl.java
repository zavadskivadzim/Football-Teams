package com.zavadski.dao;

import com.zavadski.dao.api.TeamDtoDao;
import com.zavadski.model.dto.TeamWithPlayerDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class TeamDtoDaoImpl implements TeamDtoDao {

    private final Logger logger = LogManager.getLogger(TeamDtoDaoImpl.class);

    private final EntityManager entityManager;

    @Value("${findAllTeamsWithNumberOfPlayersSql}")
    private String findAllWithNumberOfPlayersSql;

    @Autowired
    public TeamDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TeamWithPlayerDto> findAllTeamsWithNumberOfPlayers() {

        logger.debug("Start: findAllWithNumberOfPlayers");


        TypedQuery<TeamWithPlayerDto> query = entityManager.createQuery(findAllWithNumberOfPlayersSql, TeamWithPlayerDto.class);
        List<TeamWithPlayerDto> teams = query.getResultList();

//        List<TeamWithPlayerDto> teams =
//                entityManager.createQuery(findAllWithNumberOfPlayersSql, TeamWithPlayerDto.class)
//                        .setFirstResult(0)
//                        .setMaxResults(1)
//                        .getResultList();

        return teams;
    }

}
