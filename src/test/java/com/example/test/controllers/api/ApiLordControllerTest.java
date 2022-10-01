package com.example.test.controllers.api;

import com.example.test.model.domain.Planet;
import com.example.test.model.domain.Lord;
import com.example.test.repository.LordRepository;
import com.example.test.repository.PlanetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Sets;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ApiLordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private LordRepository lordRepository;

    @After
    public void resetDb() {
        planetRepository.deleteAll();
        lordRepository.deleteAll();
    }

    @Test
    public void addLord() throws Exception {
        Lord lord = new Lord("ПервыйПовелитель", 56);

        mockMvc.perform(
                post("/api/lord")
                        .content(objectMapper.writeValueAsString(lord))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

        assertNotEquals(0, ((ArrayList<Lord>) lordRepository.findAll()).size());

    }

    @Test
    public void addNotValidLord() throws Exception {

        Lord lord = new Lord();
        lord.setName("123");


        mockMvc.perform(
                post("/api/lord")
                        .content(objectMapper.writeValueAsString(lord))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());

    }

    @Test
    public void addNotValidLord2() throws Exception {

        Lord lord = new Lord();
        lord.setName("  ");
        lord.setAge(40);

        mockMvc.perform(
                post("/api/lord")
                        .content(objectMapper.writeValueAsString(lord))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());


    }

    @Test
    public void getLoungers() throws Exception {

        Planet planet = new Planet();
        planet.setName("Планета");

        Lord lord1 = new Lord(
                "ПервыйПовелитель",
                56,
                new HashSet<>(Collections.singleton(planet))
        );
        planet.setLord(lord1);

        Lord lord2 = new Lord("ВторойПовелитель", 56);
        Lord lord3 = new Lord("ТретийПовелитель", 56);

        lordRepository.save(lord1);
        lordRepository.save(lord2);
        lordRepository.save(lord3);


        mockMvc.perform(
                get("/api/lords/loungers")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));

    }

    @Test
    public void getYoungestLords() throws Exception {
        Lord lord1 = new Lord("1", 76);
        Lord lord2 = new Lord( "2", 32);
        Lord lord3 = new Lord( "3", 54);
        Lord lord4 = new Lord( "4", 45);
        Lord lord5 = new Lord( "5", 57);
        Lord lord6 = new Lord( "6", 78);
        Lord lord7 = new Lord( "7", 71);
        Lord lord8 = new Lord( "8", 44);
        Lord lord9 = new Lord("9", 66);
        Lord lord10 = new Lord( "10", 77);
        Lord lord11 = new Lord( "11", 58);
        Lord lord12 = new Lord( "12", 51);

        Set<Lord> lords = new HashSet<>(Sets.newLinkedHashSet(lord1, lord2, lord3, lord4,
                lord5, lord6, lord7, lord8,
                lord9, lord10, lord11, lord12));

        saveLords(lords);

        mockMvc.perform(
                get("/api/lords/youngest")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(32))
                .andExpect(jsonPath("$[9].age").value(76))
                .andExpect(jsonPath("$.*", hasSize(10)));
    }



    public Lord saveLord(Lord lord) {
        return lordRepository.save(lord);
    }

    public void saveLords(Set<Lord> lords) {
        for (Lord lord : lords) {
            lordRepository.save(lord);
        }
    }

    public void savePlanet(Planet planet) {
        planetRepository.save(planet);
    }
}