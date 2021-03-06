package com.zavadski.service.impl;

import com.zavadski.dao.api.PlayerFilterDtoDao;
import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerFilterDtoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerFilterDtoServiceImpl implements PlayerFilterDtoService {

    private final PlayerFilterDtoDao playerFilterDtoDao;

    public PlayerFilterDtoServiceImpl(PlayerFilterDtoDao playerFilterDtoDao) {
        this.playerFilterDtoDao = playerFilterDtoDao;
    }

    @Override
    public List<PlayerDto> filterPlayersByBirthday(LocalDate startDate, LocalDate endDate) {
        return playerFilterDtoDao.filterByBirthday(startDate, endDate);
    }
}
