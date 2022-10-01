package com.example.test.controllers.api;

import com.example.test.model.domain.Lord;
import com.example.test.model.domain.Planet;
import com.example.test.repository.LordRepository;
import com.example.test.repository.PlanetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ApiPlanetControllerTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private LordRepository lordRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @After
    public void resetDb() {
        planetRepository.deleteAll();
        lordRepository.deleteAll();
    }

    @Test
    public void setLord() throws Exception {
        Planet planet = new Planet();
        planet.setName("Планета");
        planetRepository.save(planet);

        Lord lord = new Lord();
        lord.setName("Лорд");
        lord.setAge(55);
        lordRepository.save(lord);

        mockMvc.perform(
                        post("/api/planet/" + planet.getId() + "/lord/" + lord.getId())
                                .content(objectMapper.writeValueAsString(lord))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        planet = planetRepository.findById(planet.getId()).get();
        assertNotEquals(null, planet.getLord());
    }


    @Test
    public void setNotExistLord() throws Exception {
        Planet planet = new Planet();
        planet.setName("Планета");

        planetRepository.save(planet);

        mockMvc.perform(post("/api/planet/" + planet.getId() + "/lord/" + 3))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPlanet() throws Exception {
        Planet planet = new Planet();
        planet.setName("Планета");

        mockMvc.perform(
                        post("/api/planet")
                                .content(objectMapper.writeValueAsString(planet))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        List<Planet> planetList = (List<Planet>) planetRepository.findAll();
        assertNotEquals(0, planetList.size());
    }

    @Test
    public void addNotValidPlanet() throws Exception {
        Planet planet = new Planet();
        //planet.setName("Планета");

        mockMvc.perform(
                        post("/api/planet")
                                .content(objectMapper.writeValueAsString(planet))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    public void deletePlanet() throws Exception {

        Planet planet = new Planet();
        planet.setName("Планета");
        planetRepository.save(planet);

        mockMvc.perform(
                        delete("/api/planet/" + planet.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        List<Planet> planetList = (List<Planet>) planetRepository.findAll();
        assertEquals(0, planetList.size());

    }

    @Test
    public void deleteNotExistPlanet() throws Exception {

        mockMvc.perform(
                        delete("/api/planet/2")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());

    }
}