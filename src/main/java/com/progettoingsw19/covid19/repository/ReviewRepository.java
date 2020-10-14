package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Review;
import com.progettoingsw19.covid19.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

     List<Review>getAllReviewsByIdStructure(Structure idStructure);
}
