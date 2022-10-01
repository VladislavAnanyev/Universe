package com.example.test.service;

import com.example.test.model.domain.Lord;
import com.example.test.model.domain.Planet;
import com.example.test.model.dto.PlanetDTO;
import com.example.test.repository.LordRepository;
import com.example.test.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final LordRepository lordRepository;

    @Transactional
    public void setLord(Long planetId, Long lordId) {
        Optional<Planet> optionalPlanet = planetRepository.findById(planetId);
        Optional<Lord> optionalLord = lordRepository.findById(lordId);

        if (optionalPlanet.isEmpty()) {
            throw new EntityNotFoundException("Планета не найдена");
        }

        if (optionalLord.isEmpty()) {
            throw new EntityNotFoundException("Лорд не найден");
        }

        Planet existPlanet = optionalPlanet.get();
        Lord lord = optionalLord.get();
        existPlanet.setLord(lord);
    }

    public PlanetDTO addPlanet(String planetName) {
        Planet planet = new Planet();
        planet.setName(planetName);
        planetRepository.save(planet);

        return new PlanetDTO()
                .setId(planet.getId())
                .setName(planet.getName())
                .setOwner(null);

    }

    public void deletePlanet(Long id) {

        if (planetRepository.existsById(id)) {
            planetRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ArrayList<Planet> getAllPlanets() {
        return (ArrayList<Planet>) planetRepository.findAll();
    }

    public Planet getPlanetById(Long id) {
        Optional<Planet> optionalPlanet = planetRepository.findById(id);
        if (optionalPlanet.isPresent()) {
            return optionalPlanet.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
