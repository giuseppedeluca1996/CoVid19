package com.progettoingsw19.covid19.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;


    @Column(name = "RATING")
    @NotNull
    private BigDecimal rating;


    @Column(name = "DESCRIPTION")
    @NotNull
    private String description;

    @Column(name = "DATE")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @Column(name = "QUALITYPRICE", nullable = true)
    @NotNull
    private BigDecimal qualityPrice;

    @Column(name = "CLEANING", nullable = true)
    @NotNull
    private BigDecimal cleaning;

    @Column(name = "SERVICE", nullable = true)
    @NotNull
    private BigDecimal service;

    @ManyToOne
    @JoinColumn(name = "IDUSER", nullable = false)
    private  User idUser;

    @ManyToOne
    @JoinColumn(name = "IDSTRUCTURE", nullable = false)
    private  Structure idStructure;


    public Review(Integer id, BigDecimal rating, String description, Date date, BigDecimal qualityPrice, BigDecimal cleaning, BigDecimal service) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.date = date;
        this.qualityPrice = qualityPrice;
        this.cleaning = cleaning;
        this.service = service;
    }

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

    public BigDecimal getService() { return service; }

    public void setService(BigDecimal service) {
        this.service = service;
    }

    @JsonIgnore
    public User getIdUser() {
        return idUser;
    }

    @JsonIgnore
    public Structure getIdStructure() {
        return idStructure;
    }

    @JsonIgnore
    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @JsonIgnore
    public void setIdStructure(Structure idStructure) {
        this.idStructure = idStructure;
    }
}
