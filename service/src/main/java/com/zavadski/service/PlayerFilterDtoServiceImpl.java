package com.zavadski.service;

import com.zavadski.dao.api.PlayerFilterDtoDao;
import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.api.PlayerFilterDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerFilterDtoServiceImpl implements PlayerFilterDtoService {

    private final PlayerFilterDtoDao playerFilterDtoDao;

    public PlayerFilterDtoServiceImpl(PlayerFilterDtoDao playerFilterDtoDao) {
        this.playerFilterDtoDao = playerFilterDtoDao;
    }

    @Override
    @Transactional
    public List<PlayerDto> filterPlayersByBirthday(LocalDate startDate, LocalDate endDate) {
        return playerFilterDtoDao.filterByBirthday(startDate, endDate);
    }
}
