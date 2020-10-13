package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface StructureRepository extends JpaRepository<Structure,Integer> {

    @Query("SELECT s FROM Structure AS s WHERE s.latitude = ?1 AND s.longitude = ?2")
    Structure findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    @Query("DELETE  FROM Structure AS s WHERE s.latitude = ?1 AND s.longitude = ?2")
    void deleteByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);

    Page<Structure> findAll(Pageable pageable);
    Page<Structure> findAllByTypeIs(Pageable pageable, Type type);

    @Query("SELECT s FROM Structure AS s WHERE (s.name  LIKE CONCAT('%',:text,'%')) OR (s.address LIKE CONCAT('%',:text,'%')) OR (s.city LIKE CONCAT('%',:text,'%')) OR (s.state LIKE CONCAT('%',:text,'%')) ")
    Page<Structure> findByNameOrAddressOrCityOrState(Pageable pageable, String text);

    @Query("SELECT s FROM Structure AS s WHERE ((s.name  LIKE CONCAT('%',:text,'%')) OR (s.address LIKE CONCAT('%',:text,'%')) OR (s.city LIKE CONCAT('%',:text,'%')) OR (s.state LIKE CONCAT('%',:text,'%'))) AND s.type =  :type")
    Page<Structure> findByNameOrAddressOrCityOrStateAndTypeEquals(Pageable pageable, String text, Type type);

}
