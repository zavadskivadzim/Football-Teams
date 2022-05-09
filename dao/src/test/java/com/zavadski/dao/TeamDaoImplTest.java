//package com.zavadski.dao;
//
//import com.zavadski.model.Team;
//import org.hibernate.Session;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class TeamDaoImplTest {
//
//    @InjectMocks
//    private TeamDaoImpl teamDao;
//
//    @Mock
//    private EntityManager entityManager;
//
//    @Mock
//    Query query;
//
//    @AfterEach
//    public void check() {
//        Mockito.verifyNoMoreInteractions(entityManager);
//    }
//
//    @Test
//    public void findAllTeams() {
//
//        List<Team> teams = new ArrayList<>();
//        Team testTeam = new Team();
//        teams.add(testTeam);
//
//        when(entityManager.unwrap(any())).thenReturn(any());
//
//        when(session.createQuery(anyString())).thenReturn(query);
//        when(query.getResultList()).thenReturn(teams);
////
////        List<Team> userListActual = teamDao.findAll();
////        assertNotNull(userListActual);
////
////        assertNotNull(userListActual);
//
//    }
//
//}