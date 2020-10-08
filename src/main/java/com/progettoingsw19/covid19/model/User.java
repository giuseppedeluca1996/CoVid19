package com.progettoingsw19.covid19.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="USER")
@NoArgsConstructor
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME", unique = true)
    @NotNull
    private String username;

    @Column(name = "EMAIL", unique = true)
    @NotNull
    private String email;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "SURNAME")
    @NotNull
    private String surname;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATEOFBIRTH")
    @NotNull
    private Date dateOfBirth;


    @Column(name = "PREFERENCESVIEW")
    @NotNull
    @Type(type="boolean")
    private Boolean preferencesView;


    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = { @JoinColumn(name = "IDUSER", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "IDROLE", referencedColumnName = "ID") }
    )
    @JsonManagedReference
    @JsonIgnore
    private  List<Role> roles;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Boolean getPreferencesView() {
        return preferencesView;
    }

    public void setPreferencesView(Boolean preferencesView) {
        this.preferencesView = preferencesView;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    public Set<Review> getReviews() {
        return reviews;
    }

    @JsonIgnore
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
