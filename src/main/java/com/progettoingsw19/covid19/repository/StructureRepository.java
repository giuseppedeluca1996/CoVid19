package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;


public interface StructureRepository extends JpaRepository<Structure,Integer> {

    @Query("SELECT s FROM Structure AS s WHERE s.latitude = :latitude AND s.longitude = :longitude")
    Structure findByLatitudeAndLongitude(@Param("latitude")BigDecimal latitude,@Param("longitude") BigDecimal longitude);

    @Query("DELETE  FROM Structure AS s WHERE s.latitude = :latitude AND s.longitude = :longitude")
    void deleteByLatitudeAndLongitude(@Param("latitude")BigDecimal latitude, @Param("longitude")BigDecimal longitude);

    Page<Structure> findAll(Pageable pageable);

    Page<Structure> findAllByTypeIs(Pageable pageable, Type type);

    @Query("SELECT s FROM Structure AS s WHERE (s.name  LIKE CONCAT('%',:text,'%')) OR (s.address LIKE CONCAT('%',:text,'%')) OR (s.city LIKE CONCAT('%',:text,'%')) OR (s.state LIKE CONCAT('%',:text,'%')) ")
    Page<Structure> findByNameOrAddressOrCityOrState(Pageable pageable, @Param("text") String text);

    @Query("SELECT s FROM Structure AS s WHERE ((s.name  LIKE CONCAT('%',:text,'%')) OR (s.address LIKE CONCAT('%',:text,'%')) OR (s.city LIKE CONCAT('%',:text,'%')) OR (s.state LIKE CONCAT('%',:text,'%'))) AND s.type =  :type")
    Page<Structure> findByNameOrAddressOrCityOrStateAndTypeEquals(Pageable pageable, @Param("text") String text, @Param("type") Type type);


    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <= :distance", nativeQuery = true)
    Collection<Structure> getStructureAtDistance( @Param("latitude") BigDecimal latitude, @Param("longitude")  BigDecimal longitude,  @Param("distance") BigDecimal distance);

    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <= 30  AND s.type=:#{#type.name()}", nativeQuery = true)
    Collection<Structure> getStructureAroundYou( @Param("latitude") BigDecimal latitude, @Param("longitude")  BigDecimal longitude, @Param("type")  Type type);

    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <=30", nativeQuery = true)
    Collection<Structure> getStructureAroundYou( @Param("latitude") BigDecimal latitude,  @Param("longitude") BigDecimal longitude);
}
