package com.example.test.controllers.web;

import com.example.test.model.domain.Planet;
import com.example.test.service.LordService;
import com.example.test.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        planetService.addPlanet(planet.getName());
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

    @PostMapping(path = "/planet/{id}/lord/{lordId}")
    public String setLordView(@PathVariable Long id, @PathVariable Long lordId) {
        planetService.setLord(id, lordId);
        return "redirect:/planets";
    }



}
