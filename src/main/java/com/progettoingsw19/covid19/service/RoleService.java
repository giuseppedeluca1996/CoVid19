package com.progettoingsw19.covid19.service;

import com.progettoingsw19.covid19.model.RoleEnum;
import com.progettoingsw19.covid19.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public RoleEnum getName(Integer id){
        return roleRepository.findNameById(id);
    }

}
