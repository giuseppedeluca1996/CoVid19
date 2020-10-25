package com.progettoingsw19.covid19.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer id;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "password")
    @NotNull
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    @NotNull
    private Date dateOfBirth;

    @Column(name = "preferences_view")
    @NotNull
    private Boolean preferencesView;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;


    @OneToMany(mappedBy = "idUser", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "id_user", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id_role", referencedColumnName = "id") }
    )
    @JsonManagedReference
    @JsonIgnore
    private List<Roles> roles;


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
    public List<Roles> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
