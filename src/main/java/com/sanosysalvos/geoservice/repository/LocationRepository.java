package com.sanosysalvos.geoservice.repository;

import com.sanosysalvos.geoservice.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByZone(String zone);

    List<Location> findByPetId(Long petId);

    @Query("SELECT l FROM Location l WHERE l.reportedAt BETWEEN :startDate AND :endDate")
    List<Location> findByDateRange(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);

    long countByZone(String zone);
}