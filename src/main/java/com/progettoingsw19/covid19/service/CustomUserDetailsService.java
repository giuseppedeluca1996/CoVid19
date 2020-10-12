package com.progettoingsw19.covid19.service;


import com.progettoingsw19.covid19.model.Role;
import com.progettoingsw19.covid19.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userService.getUserByUsername(usernameOrEmail);
        if(user != null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role r : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(r.getName().name()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        else if ((user=userService.getUserByEmail(usernameOrEmail))!= null){
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role r : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(r.getName().name()));
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException("");
        }
    }






}
