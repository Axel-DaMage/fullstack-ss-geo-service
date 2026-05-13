package com.sanosysalvos.geoservice.service;

import com.sanosysalvos.geoservice.model.Location;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class LocationFactory {

    public Location createLocation(Double latitud, Double longitud, String zona, Long mascotaId) {
        Location location = new Location();
        location.setLatitud(latitud);
        location.setLongitud(longitud);
        location.setZonaNombre(zona);
        location.setMascotaId(mascotaId);
        location.setReportadoEn(LocalDateTime.now());
        return location;
    }

    public Location createLocationWithAddress(Double latitud, Double longitud, String zona, String direccion, Long mascotaId) {
        Location location = createLocation(latitud, longitud, zona, mascotaId);
        location.setDireccion(direccion);
        return location;
    }
}