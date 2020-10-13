package com.progettoingsw19.covid19.controller;

import com.progettoingsw19.covid19.model.User;
import com.progettoingsw19.covid19.service.UserService;
import com.progettoingsw19.covid19.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/getAllUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getAllUser(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return userService.getAllUser(page, size);
    }
    @GetMapping(value = "/getAllUser/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getAllUser(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@PathVariable("userId") String usernameOrEmail) {
        return userService.getAllUserByText(page, size, usernameOrEmail);
    }

    @GetMapping(value = "/getUser", params = "userid")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUserById(@RequestParam("userid") Integer id) {
        return userService.getUserById(id);
    }


    @GetMapping(value = "/getUser", params = "username")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUserByUsername(@RequestParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping(value = "/getUser", params = "email")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping(value = "/deleteUser/{userid}" )
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable(name = "userid") Integer id){
        userService.deleteUserById(id);
    }

    @DeleteMapping(value = "/deleteUser",params = "username" )
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserByUsername(@RequestParam(name = "username") String username){
        userService.deleteUserByUsername(username);
    }

    @DeleteMapping(value = "/deleteUser",params = "email" )
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserByEmail(@RequestParam(name = "email") String email){
        userService.deleteUserByEmail(email);
    }

    @PostMapping("/public/insertUser")
    public void insertUser( @RequestBody User user){ userService.insert(user); }

    @PutMapping(value = "/updateUser", params = "userid")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUserById( @RequestBody User user, @RequestParam(name = "userid") Integer id){ userService.updateById(user, id); }

    @PutMapping(value = "/updateUser", params = "email")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUserByEmail( @RequestBody User user, @RequestParam(name = "email") String email){ userService.updateByEmail(user, email); }

    @PutMapping(value = "/updateUser",params = "username")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUserByUsername( @RequestBody User user, @RequestParam(name = "username") String username){ userService.updateByUsername(user, username); }


    @GetMapping( value = "/isAdmin", params = "token")
    public Boolean isAdmin (@RequestParam (name = "token")String token){
        return jwtUtil.isAdmin(token);
    }
}
