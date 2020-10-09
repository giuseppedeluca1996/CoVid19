package com.progettoingsw19.covid19.controller;


import com.progettoingsw19.covid19.model.AuthRequest;
import com.progettoingsw19.covid19.service.StructureService;
import com.progettoingsw19.covid19.service.UserService;
import com.progettoingsw19.covid19.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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

    @PostMapping("/public/login")
    public String welcome(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken((authRequest.getUsername()==null) ? authRequest.getEmail() : authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());

    }

}
