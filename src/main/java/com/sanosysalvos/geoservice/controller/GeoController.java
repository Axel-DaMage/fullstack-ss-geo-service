package com.sanosysalvos.geoservice.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class GeoController {

    @GetMapping("/health")
    public String health() {
        return "Geo Service is running";
    }

    @GetMapping("/zones")
    public Map<String, Object> getZones() {
        // Mock Implementation
        Map<String, Object> zone = new HashMap<>();
        zone.put("id", 1);
        zone.put("name", "Zona Centro");
        zone.put("incidentCount", 5);
        zone.put("lat", -33.4372);
        zone.put("lng", -70.6506);

        Map<String, Object> response = new HashMap<>();
        response.put("zones", Collections.singletonList(zone));

        return response;
    }
}
