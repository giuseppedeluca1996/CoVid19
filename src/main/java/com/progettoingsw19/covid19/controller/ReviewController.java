package com.progettoingsw19.covid19.controller;

import com.progettoingsw19.covid19.model.AvgRatingReviewOfStructure;
import com.progettoingsw19.covid19.model.AvgRatingReviewOfUser;
import com.progettoingsw19.covid19.model.Review;
import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/public/getAllReviewOfStructure")
    public List<Review> getAllReviewOfStructure(@RequestParam Integer idStructure){
        return reviewService.getAllReviewByIdStructure(idStructure);
    }

    @GetMapping(value = "/public/getAverageRatingOfStructure", params = {"idStructure"})
    public Double getAverageOfStructure(@RequestParam(name = "idStructure") Integer idStructure){
        return reviewService.getAverageOfRatingOfStructure(idStructure);
    }

    @GetMapping(value = "/public/getAverageRatingOfStructure", params = {"idStructure","year"})
    public Double getAverageOfStructure(@RequestParam(name = "idStructure") Integer idStructure,@NonNull @RequestParam("year") Integer year){
        return reviewService.getAverageOfRatingOfStructure(idStructure,year);
    }


    @GetMapping(value = "/getAverageReviewOfUser", params = "idUser")
    public Double getAverageRatingOfUserById(@RequestParam(name = "idUser") Integer idUser){
        return reviewService.getAverageOfRatingOfUserById(idUser);
    }

    @GetMapping(value = "/numberOfUserReviews", params = "idUser")
    public Integer getNumberOfUserReviewsById(@RequestParam(name = "idUser") Integer idUser){
        return reviewService.getNumberOfUserReviewsByIdUser(idUser);
    }

    @GetMapping(value = "/averageReviewOfUser", params = {"idUser", "year"} )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AvgRatingReviewOfUser> AvgRatingOfReviewOfUser(@RequestParam("idUser") Integer id, @RequestParam("year") Integer year){
        return reviewService.AvgRatingOfReviewOfUser(id, year);
    }

    @GetMapping(value = "/averageReviewOStructure", params = {"idStructure", "year"} )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AvgRatingReviewOfStructure> AvgRatingOfReviewOfStructure(@RequestParam("idStructure") Integer id, @RequestParam("year") Integer year){
        return reviewService.AvgRatingOfReviewOfStructure(id, year);
    }

    @GetMapping(value = "/getTotalNumberOfReviewOfStructure")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer getTotalNumberOfReviewOfStructure(@RequestParam(name = "idStructure") Integer id){
        return reviewService.getTotalNumberOfReviewOfStructure(id);
    }


    @GetMapping(value = "/public/averageReviewOfStructureInYearAndMonth", params = {"id", "year", "month"})
    public Double AvgRatingOfReviewOfUser(@RequestParam(name = "id") Integer id, @RequestParam("year") Integer year, @RequestParam("month") Integer month){
        return reviewService.AvgRatingOfReviewOfStructureInYearAndMonth(id, year,month);
    }
}
