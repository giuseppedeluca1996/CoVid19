package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    void deleteByUsername(String username);

    void deleteByEmail(String email);

    @Query(value = "SELECT us.id_role FROM users AS u inner join user_role as us on u.id=us.id_user WHERE u.username = ?1 OR u.email = ?1", nativeQuery = true)
    List<Integer> getRolesByUsernameOrEmail(String usernameOrEmail);

    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT u " +
            "FROM User AS u " +
            "WHERE u.email  LIKE CONCAT('%',:text,'%') " +
            "OR  u.username  LIKE CONCAT('%',:text,'%') " +
            "OR u.name  LIKE CONCAT('%',:text,'%') " +
            "OR  u.surname  LIKE CONCAT('%',:text,'%')")
    Page<User> findAllByUsernameOrEmailOrNameOrSurname(Pageable pageable, String text);

}
