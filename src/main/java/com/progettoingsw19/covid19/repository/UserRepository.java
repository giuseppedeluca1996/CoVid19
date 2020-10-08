package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
    User findByEmail(String email);

    void deleteByUsername(String username);
    void deleteByEmail(String email);
}
