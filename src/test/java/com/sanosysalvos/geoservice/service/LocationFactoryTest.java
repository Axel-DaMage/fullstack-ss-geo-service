package com.sanosysalvos.geoservice.service;

import com.sanosysalvos.geoservice.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationFactoryTest {

    private LocationFactory locationFactory;

    @BeforeEach
    void setUp() {
        locationFactory = new LocationFactory();
    }

    @Test
    void testCreateLocation() {
        Location location = locationFactory.createLocation(-33.4, -70.6, "Centro", 100L);
        assertNotNull(location);
        assertEquals(-33.4, location.getLatitud());
        assertEquals(-70.6, location.getLongitud());
        assertEquals("Centro", location.getZonaNombre());
        assertEquals(100L, location.getMascotaId());
        assertNotNull(location.getReportadoEn());
        assertNull(location.getDireccion());
    }

    @Test
    void testCreateLocationWithAddress() {
        Location location = locationFactory.createLocationWithAddress(-33.4, -70.6, "Centro", "Av. Siempre Viva 123", 100L);
        assertNotNull(location);
        assertEquals(-33.4, location.getLatitud());
        assertEquals(-70.6, location.getLongitud());
        assertEquals("Centro", location.getZonaNombre());
        assertEquals(100L, location.getMascotaId());
        assertEquals("Av. Siempre Viva 123", location.getDireccion());
        assertNotNull(location.getReportadoEn());
    }
}
