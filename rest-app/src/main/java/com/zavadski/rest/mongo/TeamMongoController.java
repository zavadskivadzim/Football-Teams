//package com.zavadski.rest.mongo;
//
//import com.zavadski.mongo.document.PlayersByAge;
//import com.zavadski.mongo.model.PlayerMongo;
//import com.zavadski.mongo.repository.PlayersByAgeRepository;
//import com.zavadski.service.PlayerService;
//import com.zavadski.service.TeamService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.stream.Collectors;
//
//@RestController
//public class TeamMongoController {
//
//    private PlayerService playerService;
//    private TeamService teamService;
//    private PlayersByAgeRepository repository;
//
//    public TeamMongoController(PlayerService playerService, TeamService teamService, PlayersByAgeRepository repository) {
//        this.playerService = playerService;
//        this.teamService = teamService;
//        this.repository = repository;
//    }
//
//    @GetMapping("/write_to_mongo1")
//    public void writeToMongo() {
//
//        PlayersByAge playersByAge = new PlayersByAge(
//                LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, uuuu")),
//                "under 18",
//                teamService.findTeamById(1).getTeamName(),
//                (playerService.getAllPlayers().stream()
//                        .map(PlayerMongo::fromPlayer)
//                        .filter(playerMongo -> playerMongo.getAge() < 18)
//                        .collect(Collectors.toList()))
//        );
//        repository.insert(playersByAge);
//    }
//}