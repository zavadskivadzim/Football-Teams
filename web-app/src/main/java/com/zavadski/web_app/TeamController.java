package com.zavadski.web_app;

import com.zavadski.model.Team;
import com.zavadski.service.TeamService;
import com.zavadski.service.TeamWithPlayerDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamWithPlayerDtoService teamDtoService;
    private final TeamService teamService;

    public TeamController(TeamWithPlayerDtoService teamDtoService,
                          TeamService teamService) {
        this.teamDtoService = teamDtoService;
        this.teamService = teamService;
    }

    /**
     * Goto teams list page.
     *
     * @return view name
     */
    @GetMapping(value = "/teams")
    public final String teams(Model model) {
        model.addAttribute("teams", teamDtoService.findAllTeamsWithNumberOfPlayers());
        model.addAttribute("count", teamService.count());
        return "teams";
    }

    /**
     * Goto edit team page.
     *
     * @return view name
     */
    @GetMapping(value = "/team/{id}")
    public final String gotoEditTeamPage(@PathVariable Integer id, Model model) {

        logger.debug("gotoEditTeamPage(id:{},model:{})", id, model);

        model.addAttribute("isNew", false);
        model.addAttribute("team", teamService.findTeamById(id));
        return "team";
    }

    /**
     * Goto new team page.
     *
     * @return view name
     */
    @GetMapping(value = "/team")
    public final String gotoAddTeamPage(Model model) {

        logger.debug("gotoAddTeamPage({})", model);

        model.addAttribute("isNew", true);
        model.addAttribute("team", new Team());
        return "team";
    }

    /**
     * Persist new team into persistence storage.
     *
     * @param team new team with filled data.
     * @return view name
     */
    @PostMapping(value = "/team")
    public String addTeam(@Valid Team team,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {

        logger.debug("addTeam({})", team);

        if (result.hasErrors()) {
            return "team";
        } else {
            if (teamService.checkTeamOnUnique(team.getTeamName())) {
                teamService.createTeam(team);
                return "redirect:/teams";
            } else {
                redirectAttributes.addAttribute("errorMessage",
                        "Team with name " + team.getTeamName() + " already exist");
                return "redirect:/errors";
            }
        }
    }

    /**
     * Update team.
     *
     * @param team team with filled data.
     * @return view name
     */
    @PostMapping(value = "/team/{id}")
    public String updateTeam(@Valid @ModelAttribute("team") Team team,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {

        logger.debug("updateTeam({})", team);

        if (result.hasErrors()) {
            return "team";
        } else {
            Team newTeam = new Team();
            newTeam.setTeamName(team.getTeamName());
            if (this.teamService.checkTeamOnUnique(newTeam.getTeamName())
                    || (Objects.equals(this.teamService.findTeamById(team.getTeamId()).getTeamName(), newTeam.getTeamName()))) {
                this.teamService.updateTeam(team);
                return "redirect:/teams";
            } else {
                redirectAttributes.addAttribute("errorMessage",
                        "Team with name " + team.getTeamName() + " already exist");
                return "redirect:/errors";
            }
        }
    }

    /**
     * Delete team.
     *
     * @return view name
     */
    @GetMapping(value = "/team/{id}/delete")
    public final String deleteTeamById(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {

        logger.debug("delete({},{})", id, model);

        teamService.isTeamWithPlayers(id);
        if (teamService.isTeamWithPlayers(id)) {
            redirectAttributes.addAttribute("errorMessage",
                    "You can't delete this team, because it has players");
            return "redirect:/errors";
        } else {
            teamService.deleteTeam(id);
            return "redirect:/teams";
        }
    }
}
