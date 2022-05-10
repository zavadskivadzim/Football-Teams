package com.zavadski.dao.api;

import com.zavadski.model.dto.PlayerDto;

import java.time.LocalDate;
import java.util.List;

public interface PlayerFilterDtoDao {

    List<PlayerDto> filterByBirthday(LocalDate startDate, LocalDate endDate);

}
