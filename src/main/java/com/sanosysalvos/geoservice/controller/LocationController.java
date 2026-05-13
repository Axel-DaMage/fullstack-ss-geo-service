package com.sanosysalvos.geoservice.controller;

import com.sanosysalvos.geoservice.model.Location;
import com.sanosysalvos.geoservice.service.LocationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        try {
            Location updatedLocation = locationService.updateLocation(id, location);
            return ResponseEntity.ok(updatedLocation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        try {
            locationService.deleteLocation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/zone/{zone}")
    public ResponseEntity<List<Location>> getLocationsByZone(@PathVariable String zone) {
        return ResponseEntity.ok(locationService.getLocationsByZone(zone));
    }

    @GetMapping("/search/date-range")
    public ResponseEntity<List<Location>> getLocationsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(locationService.getLocationsByDateRange(startDate, endDate));
    }

    @GetMapping("/totals/zone")
    public ResponseEntity<Map<String, Long>> getTotalsByZone() {
        List<String> zones = List.of("Las Condes", "Providencia", "Maipú", "Ñuñoa", "Vitacura");
        Map<String, Long> totals = new java.util.HashMap<>();
        for (String zone : zones) {
            totals.put(zone, locationService.countLocationsByZone(zone));
        }
        return ResponseEntity.ok(totals);
    }
}