package com.progettoingsw19.covid19.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AvgRatingReviewOfUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private Integer month;

    private Integer number_of_review;

    private Double  avg_rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getNumber_of_review() {
        return number_of_review;
    }

    public void setNumber_of_review(Integer number_of_review) {
        this.number_of_review = number_of_review;
    }

    public Double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }
}
