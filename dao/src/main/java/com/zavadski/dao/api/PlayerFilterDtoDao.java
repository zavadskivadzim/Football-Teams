package com.zavadski.dao.api;

import com.zavadski.model.dto.PlayerFilterDto;

import java.time.LocalDate;
import java.util.List;

public interface PlayerFilterDtoDao {

    List<PlayerFilterDto> filterByBirthday(LocalDate startDate, LocalDate endDate);

}