package com.zavadski.service.api;

import com.zavadski.model.dto.PlayerDto;

import java.time.LocalDate;
import java.util.List;

public interface PlayerFilterDtoService {

    List<PlayerDto> filterPlayersByBirthday(LocalDate startDate, LocalDate endDate);

}
