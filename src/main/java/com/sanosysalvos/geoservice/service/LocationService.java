package com.sanosysalvos.geoservice.service;

import com.sanosysalvos.geoservice.model.Location;
import com.sanosysalvos.geoservice.model.Zone;
import com.sanosysalvos.geoservice.repository.LocationRepository;
import com.sanosysalvos.geoservice.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final ZoneRepository zoneRepository;
    private final LocationFactory locationFactory;

    public LocationService(LocationRepository locationRepository, ZoneRepository zoneRepository, LocationFactory locationFactory) {
        this.locationRepository = locationRepository;
        this.zoneRepository = zoneRepository;
        this.locationFactory = locationFactory;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Transactional
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Transactional
    public Location updateLocation(Long id, Location locationDetails) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));

        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());
        location.setZone(locationDetails.getZone());
        location.setAddress(locationDetails.getAddress());
        location.setPetId(locationDetails.getPetId());

        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        locationRepository.delete(location);
    }

    public List<Location> getLocationsByZone(String zone) {
        return locationRepository.findByZone(zone);
    }

    public List<Location> getLocationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return locationRepository.findByDateRange(startDate, endDate);
    }

    public long countLocationsByZone(String zone) {
        return locationRepository.countByZone(zone);
    }

    public List<Location> getLocationsByPetId(Long petId) {
        return locationRepository.findByPetId(petId);
    }

    public Location createLocationWithZone(Double latitude, Double longitude, String zoneName, String city, Long petId) {
        Zone zone = zoneRepository.findByNameAndCity(zoneName, city)
                .orElseGet(() -> {
                    Zone newZone = new Zone();
                    newZone.setName(zoneName);
                    newZone.setCity(city);
                    newZone.setIncidenceCount(1);
                    return zoneRepository.save(newZone);
                });

        zone.setIncidenceCount(zone.getIncidenceCount() + 1);
        zoneRepository.save(zone);

        return locationFactory.createLocation(latitude, longitude, zoneName, petId);
    }
}