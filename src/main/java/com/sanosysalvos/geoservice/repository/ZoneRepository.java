package com.sanosysalvos.geoservice.repository;

import com.sanosysalvos.geoservice.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByName(String name);

    Optional<Zone> findByNameAndCity(String name, String city);
}