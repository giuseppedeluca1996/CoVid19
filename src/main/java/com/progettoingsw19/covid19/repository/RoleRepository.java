package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Role;
import com.progettoingsw19.covid19.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(RoleEnum name);

    @Query(value = "SELECT r.name FROM roles as r WHERE r.id = :id", nativeQuery = true)
    RoleEnum findNameById(@Param("id") Integer id);

}
