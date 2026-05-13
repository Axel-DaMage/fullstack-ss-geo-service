package com.sanosysalvos.geoservice.repository;

import com.sanosysalvos.geoservice.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByNombre(String nombre);

    Optional<Zone> findByNombreAndCiudad(String nombre, String ciudad);
}