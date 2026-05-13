package com.sanosysalvos.geoservice.service;

import com.sanosysalvos.geoservice.model.Location;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class LocationFactory {

    public Location createLocation(Double latitude, Double longitude, String zone, Long petId) {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setZone(zone);
        location.setPetId(petId);
        location.setReportedAt(LocalDateTime.now());
        return location;
    }

    public Location createLocationWithAddress(Double latitude, Double longitude, String zone, String address, Long petId) {
        Location location = createLocation(latitude, longitude, zone, petId);
        location.setAddress(address);
        return location;
    }
}