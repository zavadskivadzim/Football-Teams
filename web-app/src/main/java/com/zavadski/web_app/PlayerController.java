package com.zavadski.web_app;

import com.zavadski.model.Player;
import com.zavadski.service.PlayerFilterDtoService;
import com.zavadski.service.PlayerService;
import com.zavadski.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;

@Controller
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);


    private final PlayerService playerService;
    private final TeamService teamService;
    private final PlayerFilterDtoService playerDtoService;

    public PlayerController(TeamService teamService,
                            PlayerService playerService,
                            PlayerFilterDtoService playerDtoService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.playerDtoService = playerDtoService;
    }

    /**
     * Goto players list page.
     *
     * @return view name
     */
    @GetMapping(value = "/players")
    public final String players(Model model) {

        model.addAttribute("players", playerService.getAllPlayers());
        return "players";
    }

    /**
     * Goto edit player page.
     *
     * @return view name
     */
    @GetMapping(value = "/player/{id}")
    public final String gotoEditPlayerPage(@PathVariable Integer id, Model model) {

        logger.debug("gotoEditPlayerPage(id:{},model:{})", id, model);

        model.addAttribute("isNew", false);
        model.addAttribute("player", playerService.findPlayerById(id));
        model.addAttribute("teams", teamService.getAllTeams());
        return "player";
    }

    /**
     * Goto new player page.
     *
     * @return view name
     */
    @GetMapping(value = "/player")
    public final String gotoAddPlayerPage(Model model) {

        model.addAttribute("isNew", true);
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.getAllTeams());
        return "player";
    }

    /**
     * Persist new player into persistence storage.
     *
     * @param player new player with filled data.
     * @return view name
     */
    @PostMapping(value = "/player")
    public String addPlayer(@Valid @ModelAttribute("firstName") Player player,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addAttribute("errorMessage",
                    Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return "redirect:/errors";
        } else {
            this.playerService.createPlayer(player);
            return "redirect:/players";
        }
    }

    /**
     * Update player.
     *
     * @param player player with filled data.
     * @return view name
     */
    @PostMapping(value = "/player/{id}")
    public String updatePlayer(@Valid @ModelAttribute("firstName") Player player,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addAttribute("errorMessage",
                    Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return "redirect:/errors";
        } else {
            this.playerService.updatePlayer(player);
            return "redirect:/players";
        }
    }

    /**
     * Delete player.
     *
     * @return view name
     */
    @GetMapping(value = "/player/{id}/delete")
    public final String deletePlayerById(@PathVariable Integer id, Model model) {

        playerService.deletePlayer(id);
        return "redirect:/players";
    }

    @GetMapping(value = "/players/find")
    public final String filterByBirthday(@RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                         Model model,
                                         RedirectAttributes redirectAttributes) {

        if (!(startDate == null) && !(endDate == null) && endDate.isBefore(startDate)) {
            redirectAttributes.addAttribute("errorMessage",
                    "We're sorry, but we use wrong search parameters.");
            return "redirect:/errors";
        } else {
            model.addAttribute("players", playerDtoService.filterPlayersByBirthday(startDate, endDate));
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            return "players";
        }
    }
}
