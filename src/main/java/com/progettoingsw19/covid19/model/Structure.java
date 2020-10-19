package com.progettoingsw19.covid19.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="structures")
@NoArgsConstructor
public class Structure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "image_link")
    @NotNull
    private String imageLink;

    @Column(name = "site")
    @NotNull
    private String site;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "state")
    @NotNull
    private String state;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "phone")
    @NotNull
    private String phone;

    @Column(name = "type")
    @NotNull
    private Type type;

    @Column(name = "price_min")
    @NotNull
    private Double priceMin;

    @Column(name = "price_max")
    @NotNull
    private Double priceMax;

    @Column(name = "latitude")
    @NotNull
    private BigDecimal latitude;

    @Column(name = "longitude")
    @NotNull
    private BigDecimal longitude;

    @Temporal(TemporalType.TIME)
    @Column(name = "closing_hours")
    @NotNull
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date closingHours;

    @Temporal(TemporalType.TIME)
    @Column(name = "opening_hours")
    @NotNull
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date openingHours;

    @OneToMany(mappedBy = "idStructure", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Review> reviews = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(Date closingHours) {
        this.closingHours = closingHours;
    }

    public Date getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Date openingHours) {
        this.openingHours = openingHours;
    }

    @JsonIgnore
    public Set<Review> getReviews() {
        return reviews;
    }

    @JsonIgnore
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
