package com.example.test.Service;

import com.example.test.Model.Lord;
import com.example.test.Model.Planet;
import com.example.test.Repository.LordRepository;
import com.example.test.Repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final LordRepository lordRepository;

    @Transactional
    public void setLord(Long id, Lord lord) {
        Optional<Planet> optionalPlanet = planetRepository.findById(id);

        if (optionalPlanet.isPresent() && lordRepository.existsById(lord.getId())) {
            Planet existPlanet = optionalPlanet.get();
            existPlanet.setLord(lord);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Planet addPlanet(Planet planet) {
        return planetRepository.save(planet);
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
