package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Role;
import com.progettoingsw19.covid19.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(RoleEnum name);

    @Query(value = "SELECT r.name FROM role as r WHERE r.id = ?1", nativeQuery = true)
    RoleEnum findNameById(Integer id);

}
