package com.progettoingsw19.covid19.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;





@Entity
@NamedNativeQuery(
        name = "Review.AvgRatingOfReviewOfUser",
        query = "select  MONTH(r.date) AS id, MONTH(r.date) as month, COUNT(r.id_user) as number_of_review, AVG(r.rating) AS avg_rating FROM reviews AS r WHERE r.id_user = :id AND YEAR(r.date) = :year GROUP BY MONTH(r.date) ORDER BY MONTH(r.date)",
        resultSetMapping = "avgReviewOfUser"
)

@SqlResultSetMapping(
        name = "avgReviewOfUser",
        entities = {
                @EntityResult(
                        entityClass = AvgRatingReviewOfUser.class,
                        fields = {
                                @FieldResult(name = "id", column = "id"),
                                @FieldResult(name = "month", column = "month"),
                                @FieldResult(name = "number_of_review", column = "number_of_review"),
                                @FieldResult(name = "avg_rating", column = "avg_rating")

                        }
                )
        }
)
@NamedNativeQuery(
        name = "Review.AvgRatingOfReviewOfStructure",
        query = "SELECT MONTH(r.date) as id, MONTH(r.date) as month,  AVG(r.rating)as avg_rating FROM reviews AS r WHERE   YEAR(r.date) = :year AND r.id_structure = :id group by MONTH(r.date) order by MONTH(r.date) ASC",
        resultSetMapping = "avgReviewOfStructure"
)

@SqlResultSetMapping(
        name = "avgReviewOfStructure",
        entities = {
                @EntityResult(
                        entityClass = AvgRatingReviewOfStructure.class,
                        fields = {
                                @FieldResult(name = "id", column = "id"),
                                @FieldResult(name = "month", column = "month"),
                                @FieldResult(name = "avg_rating", column = "avg_rating")
                        }
                )
        }
)




@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "rating")
    @NotNull
    private BigDecimal rating;


    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @Column(name = "quality_price")
    @NotNull
    private BigDecimal qualityPrice;

    @Column(name = "cleaning")
    @NotNull
    private BigDecimal cleaning;

    @Column(name = "service")
    @NotNull
    private BigDecimal service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    @JsonIgnore
    private  User idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_structure")
    @JsonIgnore
    private  Structure idStructure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getQualityPrice() {
        return qualityPrice;
    }

    public void setQualityPrice(BigDecimal qualityPrice) {
        this.qualityPrice = qualityPrice;
    }

    public BigDecimal getCleaning() {
        return cleaning;
    }

    public void setCleaning(BigDecimal cleaning) {
        this.cleaning = cleaning;
    }

    public BigDecimal getService() {
        return service;
    }

    public void setService(BigDecimal service) {
        this.service = service;
    }

    @JsonIgnore
    public User getIdUser() {
        return idUser;
    }

    @JsonIgnore
    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @JsonIgnore
    public Structure getIdStructure() {
        return idStructure;
    }

    @JsonIgnore
    public void setIdStructure(Structure idStructure) {
        this.idStructure = idStructure;
    }
}
