//package com.zavadski.service;
//
//import com.zavadski.dao.api.PlayerFilterDtoDao;
//import com.zavadski.model.dto.PlayerFilterDto;
//import com.zavadski.service.api.PlayerFilterDtoService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@Transactional
//public class PlayerFilterDtoServiceImpl implements PlayerFilterDtoService {
//
//    private final PlayerFilterDtoDao playerFilterDtoDao;
//
//    public PlayerFilterDtoServiceImpl(PlayerFilterDtoDao playerFilterDtoDao) {
//        this.playerFilterDtoDao = playerFilterDtoDao;
//    }
//
//    @Override
//    @Transactional
//    public List<PlayerFilterDto> filterPlayersByBirthday(LocalDate startDate, LocalDate endDate) {
//        return playerFilterDtoDao.filterByBirthday(startDate, endDate);
//    }
//}
