package com.zavadski.dao;

import com.zavadski.dao.api.PlayerFilterDtoDao;
import com.zavadski.dao.exception.PlayerWrongFilterDate;
import com.zavadski.model.dto.PlayerDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
@PropertySource("sql_query.properties")
public class PlayerFilterDtoDaoImpl implements PlayerFilterDtoDao {

    private final Logger logger = LogManager.getLogger(PlayerFilterDtoDaoImpl.class);

    private final EntityManager entityManager;

    @Value("${SQL_ALL_PLAYERS}")
    private String sqlGetAllPlayers;

    @Value("${SQL_FILTER_BY_START_END_DATE}")
    private String sqlFilterByStartEndDate;

    @Value("${SQL_FILTER_BY_END_DATE}")
    private String sqlFilterByEndDate;

    @Value("${SQL_FILTER_BY_START_DATE}")
    private String sqlFilterByStartDate;

    @Autowired
    public PlayerFilterDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate) {

        logger.debug("filterByBirthday() from {} to {}", startDate, endDate);

        List<PlayerDto> players;

        if (startDate == null & endDate == null) {

            logger.debug("Don't use a filter");

            TypedQuery<PlayerDto> query = entityManager.createQuery(sqlGetAllPlayers, PlayerDto.class);
            players = query.getResultList();

        } else if (startDate == null & endDate != null) {

            logger.debug("Filter by end date");

            TypedQuery<PlayerDto> query = entityManager.createQuery(sqlFilterByEndDate, PlayerDto.class);
            query.setParameter("endDate", endDate);
            players = query.getResultList();

        } else if (endDate == null) {

            logger.debug("Filter by start date");

            TypedQuery<PlayerDto> query = entityManager.createQuery(sqlFilterByStartDate, PlayerDto.class);
            query.setParameter("startDate", startDate);
            players = query.getResultList();

        } else if (startDate.isAfter(endDate)) {

            logger.error("Start date is after end date");

            throw new PlayerWrongFilterDate("Start date is after end date");

        } else {

            logger.debug("Filter by start and end date");

            TypedQuery<PlayerDto> query = entityManager.createQuery(sqlFilterByStartEndDate, PlayerDto.class);
            query.setParameter("startDate", startDate).setParameter("endDate", endDate);
            players = query.getResultList();
        }

        return players;
    }
}
