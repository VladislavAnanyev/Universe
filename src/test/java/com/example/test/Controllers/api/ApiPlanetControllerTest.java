package com.example.test.Controllers.api;

import com.example.test.Model.Lord;
import com.example.test.Model.Planet;
import com.example.test.Repository.LordRepository;
import com.example.test.Repository.PlanetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
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
                post("/api/planet/" + planet.getId() + "/set-lord")
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

        Lord lord = new Lord();
        lord.setName("Лорд");
        lord.setAge(55);
        lord.setId(3L);


        mockMvc.perform(
                post("/api/planet/" + planet.getId() + "/set-lord")
                        .content(objectMapper.writeValueAsString(lord))
                        .contentType(MediaType.APPLICATION_JSON)
        )
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

        /*List<Planet> planetList = (List<Planet>) planetRepository.findAll();
        assertNotEquals(0, planetList.size());*/
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