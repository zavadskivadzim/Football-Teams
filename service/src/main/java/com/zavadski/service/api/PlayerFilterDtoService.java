package com.zavadski.service.api;

import com.zavadski.model.dto.PlayerFilterDto;

import java.time.LocalDate;
import java.util.List;

public interface PlayerFilterDtoService {

    List<PlayerFilterDto> filterPlayersByBirthday(LocalDate startDate, LocalDate endDate);

}
