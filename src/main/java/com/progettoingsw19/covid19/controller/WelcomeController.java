package com.progettoingsw19.covid19.controller;


import com.progettoingsw19.covid19.model.AuthRequest;
import com.progettoingsw19.covid19.model.Review;
import com.progettoingsw19.covid19.model.User;
import com.progettoingsw19.covid19.service.StructureService;
import com.progettoingsw19.covid19.service.UserService;
import com.progettoingsw19.covid19.util.JwtUtil;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
import java.util.Set;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    StructureService structureService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

    @GetMapping("test")
    public Set<Review> welcome2() {
        return structureService.getStructureById(66).getReviews();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("test2")
    public String welcome3() {
        return "efqf32rf23r2r1324312 to javatechie !!";
    }


    @PostMapping("authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @PostMapping("insert")

    public void isnertUser(@RequestBody User user) throws Exception {



       userService.insert(user);
    }
}
