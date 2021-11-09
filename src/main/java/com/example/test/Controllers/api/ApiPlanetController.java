package com.example.test.Controllers.api;

import com.example.test.Model.Lord;
import com.example.test.Model.Planet;
import com.example.test.Service.LordService;
import com.example.test.Service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class ApiPlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping(path = "/planet/{id}/set-lord")
    public void setLord(@PathVariable Long id, @Valid @RequestBody Lord lord) {
        planetService.setLord(id, lord);
    }

    @PostMapping(path = "/planet")
    public Planet addPlanet(@Valid @RequestBody Planet planet) {
        return planetService.addPlanet(planet);
    }

    @DeleteMapping(path = "/planet/{id}")
    public void deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
    }

}
