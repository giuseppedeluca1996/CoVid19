package com.progettoingsw19.covid19.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="STRUCTURE")
@NoArgsConstructor
public class Structure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "ADDRESS")
    @NotNull
    private String address;

    @Column(name = "IMAGELINk")
    @NotNull
    private String imageLink;

    @Column(name = "SITE")
    @NotNull
    private String site;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "STATE")
    @NotNull
    private String state;

    @Column(name = "CITY")
    @NotNull
    private String city;

    @Column(name = "PHONE")
    @NotNull
    private String phone;

    @Column(name = "TYPE")
    @NotNull
    private Type type;


    @Column(name = "PRICEMIN")
    @NotNull
    private Double priceMin;

    @Column(name = "PRICEMAX")
    @NotNull
    private Double priceMax;



    @Column(name = "LATITUDE")
    @NotNull
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    @NotNull
    private BigDecimal longitude;

    @Temporal(TemporalType.TIME)
    @Column(name = "CLOSINGHOURS")
    @NotNull
    private Date closingHours;

    @Temporal(TemporalType.TIME)
    @Column(name = "OPENINGHOURS")
    @NotNull
    private Date openingHours;

    @OneToMany(mappedBy = "idStructure", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Review> reviews = new HashSet<>();


    public Structure(Integer id, String name, String address, String imageLink, String site, String email, String state, String city, String phone, Type type, Double priceMin, Double priceMax, BigDecimal latitude, BigDecimal longitude, Date closingHours, Date openingHours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.imageLink = imageLink;
        this.site = site;
        this.email = email;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.type = type;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.latitude = latitude;
        this.longitude = longitude;
        this.closingHours = closingHours;
        this.openingHours = openingHours;
    }

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

    public Date getClosingHours() { return closingHours; }

    public void setClosingHours(Date closingHours) {
        this.closingHours = closingHours;
    }

    public Date getOpeningHours() { return openingHours; }

    public void setOpeningHours(Date openingHours) {
        this.openingHours = openingHours;
    }

    @JsonIgnore
    public Set<Review> getReviews() {
        return reviews;
    }
}
