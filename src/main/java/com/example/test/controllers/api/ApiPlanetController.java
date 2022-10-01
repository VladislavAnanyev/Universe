package com.example.test.controllers.api;

import com.example.test.model.domain.Planet;
import com.example.test.model.dto.CreatePlanetRequest;
import com.example.test.model.dto.PlanetDTO;
import com.example.test.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class ApiPlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping(path = "/planet/{planetId}/lord/{lordId}")
    public void setLord(@PathVariable Long planetId, @PathVariable Long lordId) {
        planetService.setLord(planetId, lordId);
    }

    @PostMapping(path = "/planet")
    public PlanetDTO addPlanet(@Valid @RequestBody CreatePlanetRequest planet) {
        return planetService.addPlanet(planet.getName());
    }

    @DeleteMapping(path = "/planet/{id}")
    public void deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
    }

}
