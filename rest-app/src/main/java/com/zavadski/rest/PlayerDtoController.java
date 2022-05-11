package com.zavadski.rest;

import com.zavadski.model.dto.PlayerDto;
import com.zavadski.service.PlayerFilterDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class PlayerDtoController {

    private static final Logger logger = LogManager.getLogger(PlayerDtoController.class);

    private final PlayerFilterDtoService playerFilterDtoService;

    public PlayerDtoController(PlayerFilterDtoService playerFilterDtoService) {
        this.playerFilterDtoService = playerFilterDtoService;
    }

    @GetMapping(value = "player_dtos")
    public final Collection<PlayerDto> filterByBirthday(@RequestParam(value = "startDate", required = false)
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @RequestParam(value = "endDate", required = false)
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        logger.debug("filter Players By Birthday");

        return playerFilterDtoService.filterPlayersByBirthday(startDate, endDate);
    }
}
