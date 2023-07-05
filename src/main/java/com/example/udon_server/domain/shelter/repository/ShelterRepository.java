package com.example.udon_server.domain.shelter.repository;

import com.example.udon_server.domain.shelter.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    @Query(value = "SELECT *, (6371 * ACOS(COS(RADIANS(:lat)) * COS(RADIANS(lat))" +
            " * COS(RADIANS(lon) - RADIANS(:lon))" +
            " + SIN(RADIANS(:lat)) * SIN(RADIANS(lat))" +
            ")) AS distance FROM shelter HAVING distance <= 10 ORDER BY distance;",
            nativeQuery = true)
    List<Shelter> findAllShelter(@Param(value = "lat") BigDecimal lat, @Param(value = "lon") BigDecimal lon);

    List<Shelter> findByShelNmContaining(String keyword);
}
