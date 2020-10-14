package com.progettoingsw19.covid19.controller;

import com.progettoingsw19.covid19.model.Review;
import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping(value = "/public/getAllReview")
    public List<Review> getAllReviewOfStructure(@RequestBody Structure structure){
        return reviewService.getAllByStructure(structure);
    }
    @PostMapping(value = "/public/getAverageRating")
    public Double getAverageOfStructure(@RequestBody Structure structure){
        return reviewService.getAverageOfRating(structure);
    }


}
