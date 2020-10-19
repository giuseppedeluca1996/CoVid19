package com.progettoingsw19.covid19.service;

import com.progettoingsw19.covid19.model.AvgRatingReviewOfStructure;
import com.progettoingsw19.covid19.model.AvgRatingReviewOfUser;
import com.progettoingsw19.covid19.model.Review;
import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.repository.ReviewRepository;
import com.progettoingsw19.covid19.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReview(){ return reviewRepository.findAll(); }

    public  Review getReviewById(Integer id){ return reviewRepository.findById(id).orElse(null); }

    public void deleteReview(Review review) { reviewRepository.delete(review); }

    public void deleteReviewById(Integer id) { reviewRepository.deleteById(id); }

    public void updateReviewById(Review newReview, Integer id) {
        Review review= reviewRepository.findById(id).orElse(null);
        if(review != null){
            review.setCleaning(newReview.getCleaning());
            review.setService(newReview.getCleaning());
            review.setQualityPrice(newReview.getQualityPrice());
            review.setDate(newReview.getDate());
            review.setDescription(newReview.getDescription());
            review.setRating(newReview.getRating());

            reviewRepository.save(review);
        }
    }

    public void insert(Review review){ reviewRepository.save(review); }

    public List<Review> getAllReviewByIdStructure(Integer idStructure){ return reviewRepository.getAllReviewsByIdStructure_Id(idStructure); }

    public List<Review> getAllReviewByIdStructure(Integer idStructure, Integer year){ return reviewRepository.getAllReviewsByIdStructure_Id(idStructure,year); }


    public Double getAverageOfRatingOfStructure(Integer idStructure){

        List<Review> reviews = getAllReviewByIdStructure(idStructure);
        Double average=0D;
        for(Review r : reviews){
            average +=  r.getRating().doubleValue();
        }
        return  (reviews.size()!=0) ? average/reviews.size() : 0D;
    }

    public Double getAverageOfRatingOfStructure(Integer idStructure, Integer year){

        List<Review> reviews = getAllReviewByIdStructure(idStructure,year);
        Double average=0D;
        for(Review r : reviews){
            average +=  r.getRating().doubleValue();
        }
        return  (reviews.size()!=0) ? average/reviews.size() : 0D;



    }

    public Double getAverageOfRatingOfUserByUsername(String username){

        List<Review> reviews = reviewRepository.getAllReviewsByIdUser_Username(username);
        Double average=0D;
        for(Review r : reviews){
            average +=  r.getRating().doubleValue();
        }
        return  (reviews.size()!=0) ? average/reviews.size() : 0D;

    }
    public Double getAverageOfRatingOfUserByEmail(String email){

        List<Review> reviews = reviewRepository.getAllReviewsByIdUser_Email(email);
        Double average=0D;
        for(Review r : reviews){
            average +=  r.getRating().doubleValue();
        }
        return  (reviews.size()!=0) ? average/reviews.size() : 0D;

    }

    public Double getAverageOfRatingOfUserById(Integer id){

        List<Review> reviews = reviewRepository.getAllReviewsByIdUser_Id(id);
        Double average=0D;
        for(Review r : reviews){
            average +=  r.getRating().doubleValue();
        }
        return  (reviews.size()!=0) ? average/reviews.size() : 0D;

    }

    public Integer getNumberOfUserReviewsByUsernameUser(String username){ return reviewRepository.getAllReviewsByIdUser_Username(username).size(); }

    public Integer getNumberOfUserReviewsByEmailUser(String email){ return reviewRepository.getAllReviewsByIdUser_Email(email).size(); }

    public Integer getNumberOfUserReviewsByIdUser(Integer idUser){ return reviewRepository.getAllReviewsByIdUser_Id(idUser).size(); }

    public List<AvgRatingReviewOfUser> AvgRatingOfReviewOfUser(Integer id, Integer year){
        return reviewRepository.AvgRatingOfReviewOfUser(id, year);
    }

    public List<AvgRatingReviewOfStructure> AvgRatingOfReviewOfStructure(Integer id, Integer year){
        return reviewRepository.AvgRatingOfReviewOfStructure(id, year);
    }

    public Double AvgRatingOfReviewOfStructureInYearAndMonth(Integer id, Integer year, Integer month){
        return reviewRepository.getAvgReviewOfStructureInYearAndMonth(id,year,month);
    }

    public Integer getTotalNumberOfReviewOfStructure(Integer id) {
        return reviewRepository.getNumberReviewByIdStructure_Id(id);
    }
}
