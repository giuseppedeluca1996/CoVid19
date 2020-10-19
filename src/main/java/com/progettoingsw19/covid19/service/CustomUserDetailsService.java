package com.progettoingsw19.covid19.service;


import com.progettoingsw19.covid19.model.Role;
import com.progettoingsw19.covid19.model.RoleEnum;
import com.progettoingsw19.covid19.model.User;
import com.progettoingsw19.covid19.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private List<RoleEnum> roleEnums;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userService.getUserByUsername(usernameOrEmail);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user != null) {
            if(roleEnums==null){
                roleEnums=new ArrayList<>();
                for (Role r : user.getRoles()){
                    roleEnums.add(r.getName());
                }
            }
            for(RoleEnum r : roleEnums){
                authorities.add(new SimpleGrantedAuthority(r.toString()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        else if ((user=userService.getUserByEmail(usernameOrEmail))!= null){
            if(roleEnums==null){
                roleEnums=new ArrayList<>();
                for (Role r : user.getRoles()){
                    roleEnums.add(r.getName());
                }
            }
            for(RoleEnum r : roleEnums){
                authorities.add(new SimpleGrantedAuthority(r.toString()));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException("");
        }
    }


    public List<RoleEnum> getRoleEnums() {
        return roleEnums;
    }

    public void setRoleEnums(List<RoleEnum> roleEnums) {
        this.roleEnums = roleEnums;
    }
}
