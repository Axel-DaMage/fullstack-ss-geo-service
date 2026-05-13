package com.sanosysalvos.geoservice.service;

import com.sanosysalvos.geoservice.model.Location;
import com.sanosysalvos.geoservice.model.Zone;
import com.sanosysalvos.geoservice.repository.LocationRepository;
import com.sanosysalvos.geoservice.repository.ZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private LocationFactory locationFactory;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLocations() {
        Location loc1 = new Location();
        Location loc2 = new Location();
        when(locationRepository.findAll()).thenReturn(Arrays.asList(loc1, loc2));

        List<Location> locations = locationService.getAllLocations();
        assertEquals(2, locations.size());
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void testGetLocationByIdFound() {
        Location loc = new Location();
        when(locationRepository.findById(1L)).thenReturn(Optional.of(loc));

        Optional<Location> result = locationService.getLocationById(1L);
        assertTrue(result.isPresent());
        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateLocation() {
        Location loc = new Location();
        when(locationRepository.save(any(Location.class))).thenReturn(loc);

        Location result = locationService.createLocation(loc);
        assertNotNull(result);
        verify(locationRepository, times(1)).save(loc);
    }

    @Test
    void testUpdateLocationFound() {
        Location existingLoc = new Location();
        Location details = new Location();
        details.setLatitud(-33.0);
        when(locationRepository.findById(1L)).thenReturn(Optional.of(existingLoc));
        when(locationRepository.save(any(Location.class))).thenReturn(existingLoc);

        Location result = locationService.updateLocation(1L, details);
        assertNotNull(result);
        verify(locationRepository, times(1)).findById(1L);
        verify(locationRepository, times(1)).save(existingLoc);
    }

    @Test
    void testDeleteLocationFound() {
        Location loc = new Location();
        when(locationRepository.findById(1L)).thenReturn(Optional.of(loc));

        locationService.deleteLocation(1L);
        verify(locationRepository, times(1)).findById(1L);
        verify(locationRepository, times(1)).delete(loc);
    }

    @Test
    void testGetLocationsByZone() {
        Location loc = new Location();
        when(locationRepository.findByZonaNombre("Centro")).thenReturn(Arrays.asList(loc));

        List<Location> result = locationService.getLocationsByZone("Centro");
        assertEquals(1, result.size());
        verify(locationRepository, times(1)).findByZonaNombre("Centro");
    }

    @Test
    void testGetLocationsByDateRange() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        Location loc = new Location();
        when(locationRepository.findByDateRange(start, end)).thenReturn(Arrays.asList(loc));

        List<Location> result = locationService.getLocationsByDateRange(start, end);
        assertEquals(1, result.size());
        verify(locationRepository, times(1)).findByDateRange(start, end);
    }

    @Test
    void testCountLocationsByZone() {
        when(locationRepository.countByZonaNombre("Centro")).thenReturn(5L);

        long count = locationService.countLocationsByZone("Centro");
        assertEquals(5L, count);
        verify(locationRepository, times(1)).countByZonaNombre("Centro");
    }

    @Test
    void testGetLocationsByPetId() {
        Location loc = new Location();
        when(locationRepository.findByMascotaId(10L)).thenReturn(Arrays.asList(loc));

        List<Location> result = locationService.getLocationsByPetId(10L);
        assertEquals(1, result.size());
        verify(locationRepository, times(1)).findByMascotaId(10L);
    }

    @Test
    void testCreateLocationWithZone() {
        Zone zone = new Zone();
        zone.setConteoIncidencias(1);
        when(zoneRepository.findByNombreAndCiudad("Centro", "Santiago")).thenReturn(Optional.of(zone));
        when(zoneRepository.save(any(Zone.class))).thenReturn(zone);

        Location loc = new Location();
        when(locationFactory.createLocation(-33.0, -70.0, "Centro", 10L)).thenReturn(loc);

        Location result = locationService.createLocationWithZone(-33.0, -70.0, "Centro", "Santiago", 10L);

        assertNotNull(result);
        verify(zoneRepository, times(1)).findByNombreAndCiudad("Centro", "Santiago");
        verify(zoneRepository, times(1)).save(zone);
        verify(locationFactory, times(1)).createLocation(-33.0, -70.0, "Centro", 10L);
    }
}
