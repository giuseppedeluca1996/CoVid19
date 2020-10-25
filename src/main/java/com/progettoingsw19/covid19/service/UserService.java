package com.progettoingsw19.covid19.service;


import com.progettoingsw19.covid19.model.User;
import com.progettoingsw19.covid19.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getAllUser(Integer page, Integer size){ return  userRepository.findAll(PageRequest.of(page, size)); }

    public Page<User> getAllUserByText(Integer page, Integer size,String text){ return  userRepository.findAllByUsernameOrEmailOrNameOrSurname(PageRequest.of(page, size),text); }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username){ return userRepository.findByUsername(username); }

    public User getUserByEmail(String email){ return userRepository.findByEmail(email); }

    @Transactional
    public void deleteUserById(Integer id){ userRepository.deleteById(id); }

    @Transactional
    public void deleteUserByUsername(String username){ userRepository.deleteByUsername(username); }

    @Transactional
    public void deleteUserByEmail(String email){ userRepository.deleteByEmail(email); }

    @Transactional
    public User updateById(User newUser, Integer id){
        User user=userRepository.findById(id).orElse(null);
        if(user != null){
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setGender(newUser.getGender());
            user.setPreferencesView(newUser.getPreferencesView());
            user.setSurname(newUser.getSurname());
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            if(passwordEncoder.matches(newUser.getPassword(), user.getPassword())){
                user.setPassword(newUser.getPassword());
            }else {
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }
            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public User updateByEmail(User newUser, String email){

        User user=userRepository.findByEmail(email);
        if(user != null){
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setGender(newUser.getGender());
            user.setPreferencesView(newUser.getPreferencesView());
            user.setSurname(newUser.getSurname());
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setEnabled(newUser.getEnabled());
            if(passwordEncoder.matches(newUser.getPassword(), user.getPassword())){
                user.setPassword(newUser.getPassword());
            }else {
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }
            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public User updateByUsername(User newUser, String username){
        User user=userRepository.findByUsername(username);
        if(user != null){
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setGender(newUser.getGender());
            user.setPreferencesView(newUser.getPreferencesView());
            user.setSurname(newUser.getSurname());
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setEnabled(newUser.getEnabled());
            if(passwordEncoder.matches(newUser.getPassword(), user.getPassword())){
                user.setPassword(newUser.getPassword());
            }else {
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }
            return userRepository.save(user);
        }
        return null;
    }

    @Transactional
    public void insert(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<Integer> getAllUserRolesByUsernameOrEmail(String  usernameOrEmail){ return userRepository.getRolesByUsernameOrEmail(usernameOrEmail); }


    public Boolean checkDisponibilityEmail(String email) {
        return userRepository.findByEmail(email) == null;
    }

    public Boolean checkDisponibilityUsername(String username) {
        return userRepository.findByUsername(username) == null;
    }
}

