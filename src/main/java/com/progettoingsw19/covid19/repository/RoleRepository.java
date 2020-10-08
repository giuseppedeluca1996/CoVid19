package com.progettoingsw19.covid19.repository;

import com.progettoingsw19.covid19.model.Role;
import com.progettoingsw19.covid19.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(RoleEnum name);

}
