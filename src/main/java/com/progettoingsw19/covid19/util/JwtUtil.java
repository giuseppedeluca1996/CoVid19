package com.progettoingsw19.covid19.util;

import com.progettoingsw19.covid19.model.RoleEnum;
import com.progettoingsw19.covid19.service.RoleService;
import com.progettoingsw19.covid19.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean extractAdmin(String token) {
        final Claims claims = extractAllClaims(token);
       return (Boolean)claims.get("admin");
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String usernameOrEmail) {

        Boolean isAdmin=false;
        List<Integer> roles= userService.getAllUserRolesByUsernameOrEmail(usernameOrEmail);
        for (Integer id: roles) {
                 if( roleService.getName(id) == RoleEnum.ROLE_ADMIN){
                     isAdmin=true;
                 }
        }
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, usernameOrEmail, isAdmin);
    }

    private String createToken(Map<String, Object> claims, String subject, Boolean isAdmin) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()  + 1000 * 60 * 60 * 10))
                .claim("admin", isAdmin)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isAdmin(String token){
        return extractAdmin(token);
    }
}

