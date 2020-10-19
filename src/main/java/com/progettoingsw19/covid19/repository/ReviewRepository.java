package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.AvgRatingReviewOfStructure;
import com.progettoingsw19.covid19.model.AvgRatingReviewOfUser;
import com.progettoingsw19.covid19.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;




public interface ReviewRepository extends JpaRepository<Review,Integer> {

     List<Review>getAllReviewsByIdStructure_Id(Integer idStructure);

     @Query(value = "select r from Review as r  where r.idStructure.id = :idStructure and YEAR(r.date) = :year")
     List<Review>getAllReviewsByIdStructure_Id(@Param("idStructure") Integer idStructure, @Param("year") Integer year);

     List<Review>getAllReviewsByIdUser_Id(Integer idUser);

     List<Review>getAllReviewsByIdUser_Email(String email);

     List<Review>getAllReviewsByIdUser_Username(String username);

     List<AvgRatingReviewOfUser> AvgRatingOfReviewOfUser(@Param("id") Integer id, @Param("year") Integer year);

     List<AvgRatingReviewOfStructure> AvgRatingOfReviewOfStructure(@Param("id") Integer id, @Param("year") Integer year);


     @Query(value = "select count(r.id) from Review  as r where r.idStructure.id = :id")
     Integer getNumberReviewByIdStructure_Id(@Param("id") Integer id);

     @Query(value ="SELECT AVG(r.rating) as media FROM reviews AS r WHERE MONTH(r.date) = :month AND YEAR(r.date) = :year AND r.id_structure = :id ", nativeQuery = true )
     Double getAvgReviewOfStructureInYearAndMonth(@Param("id") Integer id, @Param("year") Integer year, @Param("month") Integer month);

}

