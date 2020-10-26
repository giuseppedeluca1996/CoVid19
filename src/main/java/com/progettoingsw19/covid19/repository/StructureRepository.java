package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

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

    @Query("SELECT s FROM Structure AS s WHERE (s.name  LIKE CONCAT('%',:text,'%')) OR (s.address LIKE CONCAT('%',:text,'%')) OR (s.city LIKE CONCAT('%',:text,'%')) OR (s.state LIKE CONCAT('%',:text,'%')) ")
    Collection<Structure> findByNameOrAddressOrCityOrState( @Param("text") String text);

    @Query("SELECT s FROM Structure AS s WHERE ((s.name  LIKE CONCAT('%',:text,'%')) OR (s.address LIKE CONCAT('%',:text,'%')) OR (s.city LIKE CONCAT('%',:text,'%')) OR (s.state LIKE CONCAT('%',:text,'%'))) AND s.type =  :type")
    Collection<Structure> findByNameOrAddressOrCityOrStateAndTypeEquals( @Param("text") String text, @Param("type") Type type);


    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <= :distance", nativeQuery = true)
    Collection<Structure> getStructureAtDistance( @Param("latitude") BigDecimal latitude, @Param("longitude")  BigDecimal longitude,  @Param("distance") BigDecimal distance);

    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <= 50 AND s.type= :#{#type1.name()} AND s.price_min >= :priceMin AND s.price_max<= :priceMax", nativeQuery = true)
    Collection<Structure> getStructureAroundYou( @Param("latitude") BigDecimal latitude, @Param("longitude")  BigDecimal longitude, @Param("priceMin") Double priceMin, @Param("priceMax")Double priceMax ,@Param("type1") Type type1);

    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <= 50 AND(s.type= :#{#type1.name()} OR s.type= :#{#type2.name()}) AND s.price_min >= :priceMin AND s.price_max<= :priceMax", nativeQuery = true)
    Collection<Structure> getStructureAroundYou( @Param("latitude") BigDecimal latitude, @Param("longitude")  BigDecimal longitude, @Param("priceMin") Double priceMin, @Param("priceMax")Double priceMax ,@Param("type1") Type type1,@Param("type2") Type type2);

    @Query(value = "SELECT * FROM structures AS s WHERE ((SELECT st_distance_sphere(POINT(s.latitude,s.longitude), POINT( :latitude, :longitude))) /1000) <= 50 AND (s.type= :#{#type1.name()} OR s.type= :#{#type2.name()} OR s.type= :#{#type3.name()} ) AND s.price_min >= :priceMin AND s.price_max<= :priceMax", nativeQuery = true)
    Collection<Structure> getStructureAroundYou( @Param("latitude") BigDecimal latitude, @Param("longitude")  BigDecimal longitude, @Param("priceMin") Double priceMin, @Param("priceMax")Double priceMax ,@Param("type1") Type type1,@Param("type2") Type type2,@Param("type3") Type type3);





    @Query(value = "SELECT * FROM structures AS s WHERE   s.type= :#{#type1.name()} AND s.price_min >= :priceMin AND s.price_max<= :priceMax AND ((s.name  LIKE CONCAT('%',:query,'%')) OR (s.address LIKE CONCAT('%',:query,'%')) OR (s.city LIKE CONCAT('%',:query,'%')) OR (s.state LIKE CONCAT('%',:query,'%')))", nativeQuery = true)
    Collection<Structure> getStructureByText( @Param("priceMin") Double priceMin, @Param("priceMax")Double priceMax , @Param("query")String query, @Param("type1") Type type1);

    @Query(value = "SELECT * FROM structures AS s WHERE (s.type= :#{#type1.name()} OR s.type= :#{#type2.name()}) AND s.price_min >= :priceMin AND s.price_max<= :priceMax AND ((s.name  LIKE CONCAT('%',:query,'%')) OR (s.address LIKE CONCAT('%',:query,'%')) OR (s.city LIKE CONCAT('%',:query,'%')) OR (s.state LIKE CONCAT('%',:query,'%')))", nativeQuery = true)
    Collection<Structure> getStructureByText(  @Param("priceMin") Double priceMin, @Param("priceMax")Double priceMax , @Param("query")String query, @Param("type1") Type type1,@Param("type2") Type type2);

    @Query(value = "SELECT * FROM structures AS s WHERE (s.type= :#{#type1.name()} OR s.type= :#{#type2.name()} OR s.type= :#{#type3.name()} ) AND s.price_min >= :priceMin AND s.price_max<= :priceMax AND ((s.name  LIKE CONCAT('%',:query,'%')) OR (s.address LIKE CONCAT('%',:query,'%')) OR (s.city LIKE CONCAT('%',:query,'%')) OR (s.state LIKE CONCAT('%',:query,'%')))", nativeQuery = true)
    Collection<Structure> getStructureByText(  @Param("priceMin") Double priceMin, @Param("priceMax")Double priceMax , @Param("query")String query, @Param("type1") Type type1,@Param("type2") Type type2,@Param("type3") Type type3);




    @Async
    @Query("SELECT DISTINCT s.state FROM Structure AS s WHERE (s.state LIKE CONCAT('%',:text,'%'))")
    ListenableFuture<Collection<String>> getTipsState(String text);

    @Async
    @Query("SELECT DISTINCT s.city FROM Structure AS s WHERE (s.city LIKE CONCAT('%',:text,'%'))")
    ListenableFuture<Collection<String>> getTipsCity(String text);
}
