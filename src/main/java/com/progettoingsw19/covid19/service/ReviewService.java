package com.progettoingsw19.covid19.service;

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



    public List<Review> getAllByStructure(Structure s){
        return reviewRepository.getAllReviewsByIdStructure(s);
    }

    public Double getAverageOfRating(Structure s){

        List<Review> reviews = getAllByStructure(s);
        Double average=0D;
        for(Review r : reviews){
            average +=  r.getRating().doubleValue();
        }
        return  (reviews.size()!=0) ? average/reviews.size() : 0D;



    }

}
