package com.sanosysalvos.geoservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(GeoController.class)
class GeoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHealth() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Geo Service is running"));
    }

    @Test
    void testGetZones() throws Exception {
        mockMvc.perform(get("/zones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.zones", hasSize(1)))
                .andExpect(jsonPath("$.zones[0].name", is("Zona Centro")))
                .andExpect(jsonPath("$.zones[0].incidentCount", is(5)));
    }
}
