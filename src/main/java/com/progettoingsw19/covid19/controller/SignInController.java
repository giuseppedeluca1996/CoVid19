package com.progettoingsw19.covid19.controller;


import com.google.gson.Gson;
import com.progettoingsw19.covid19.model.AuthRequest;
import com.progettoingsw19.covid19.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/signin")
public class SignInController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/public/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken((authRequest.getUsername()==null) ? authRequest.getEmail() : authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }

        return jwtUtil.generateToken((authRequest.getUsername()==null) ? authRequest.getEmail() : authRequest.getUsername());

    }








}
