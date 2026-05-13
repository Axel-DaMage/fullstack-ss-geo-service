package com.sanosysalvos.geoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanosysalvos.geoservice.model.Location;
import com.sanosysalvos.geoservice.service.LocationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllLocations() throws Exception {
        Location loc = new Location();
        loc.setId(1L);
        Mockito.when(locationService.getAllLocations()).thenReturn(Arrays.asList(loc));

        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    void testGetLocationByIdFound() throws Exception {
        Location loc = new Location();
        loc.setId(1L);
        Mockito.when(locationService.getLocationById(1L)).thenReturn(Optional.of(loc));

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void testGetLocationByIdNotFound() throws Exception {
        Mockito.when(locationService.getLocationById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateLocation() throws Exception {
        Location loc = new Location();
        loc.setZonaNombre("Centro");
        
        Location createdLoc = new Location();
        createdLoc.setId(1L);
        createdLoc.setZonaNombre("Centro");

        Mockito.when(locationService.createLocation(any(Location.class))).thenReturn(createdLoc);

        mockMvc.perform(post("/api/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loc)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.zonaNombre", is("Centro")));
    }

    @Test
    void testUpdateLocationFound() throws Exception {
        Location loc = new Location();
        loc.setZonaNombre("Sur");
        
        Location updatedLoc = new Location();
        updatedLoc.setId(1L);
        updatedLoc.setZonaNombre("Sur");

        Mockito.when(locationService.updateLocation(eq(1L), any(Location.class))).thenReturn(updatedLoc);

        mockMvc.perform(put("/api/locations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.zonaNombre", is("Sur")));
    }

    @Test
    void testUpdateLocationNotFound() throws Exception {
        Location loc = new Location();
        
        Mockito.when(locationService.updateLocation(eq(1L), any(Location.class)))
                .thenThrow(new RuntimeException("Location not found"));

        mockMvc.perform(put("/api/locations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loc)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteLocationFound() throws Exception {
        Mockito.doNothing().when(locationService).deleteLocation(1L);

        mockMvc.perform(delete("/api/locations/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteLocationNotFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Location not found")).when(locationService).deleteLocation(1L);

        mockMvc.perform(delete("/api/locations/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetLocationsByZone() throws Exception {
        Location loc = new Location();
        loc.setId(1L);
        Mockito.when(locationService.getLocationsByZone("Centro")).thenReturn(Arrays.asList(loc));

        mockMvc.perform(get("/api/locations/search/zone/Centro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetTotalsByZone() throws Exception {
        Mockito.when(locationService.countLocationsByZone(any(String.class))).thenReturn(10L);

        mockMvc.perform(get("/api/locations/totals/zone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Las Condes']", is(10)));
    }
}
