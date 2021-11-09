package com.example.test.Controllers;

import com.example.test.Model.Lord;
import com.example.test.Model.Planet;
import com.example.test.Repository.PlanetRepository;
import com.example.test.Service.LordService;
import com.example.test.Service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class WebPlanetController {

    private final PlanetService planetService;
    private final LordService lordService;

    @GetMapping(path = "/planets")
    public String getAllPlanets(Model model) {
        model.addAttribute("planets", planetService.getAllPlanets());
        return "planets";
    }

    @PostMapping(path = "/planet")
    public String addPlanet(Planet planet) {
        planetService.addPlanet(planet);
        return "redirect:/planets";
    }

    @GetMapping(path = "/planet/create")
    public String createPlanetView() {
        return "create-planet";
    }

    @PostMapping(path = "/planet/{id}/delete")
    public String deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return "redirect:/planets";
    }

    @GetMapping(path = "/planet/{id}/set-lord")
    public String setLordView(@PathVariable Long id, Model model) {
        model.addAttribute("planet", planetService.getPlanetById(id));
        model.addAttribute("lords", lordService.getAllLords());
        return "set-lord";
    }

    @PostMapping(path = "/planet/{id}/set-lord")
    public String setLordView(@PathVariable Long id, @Valid Lord lord) {
        planetService.setLord(id, lord);
        return "redirect:/planets";
    }



}
