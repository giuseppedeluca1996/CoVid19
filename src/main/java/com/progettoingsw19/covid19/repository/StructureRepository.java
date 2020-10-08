package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface StructureRepository extends JpaRepository<Structure,Integer> {

    @Query("SELECT a FROM Structure a WHERE latitude = ?1 AND longitude = ?2")
    Structure findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    @Query("DELETE  FROM Structure  WHERE latitude = ?1 AND longitude = ?2")
    void deleteByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
