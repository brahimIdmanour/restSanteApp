package com.stage.crudApi.service;

import com.stage.crudApi.models.UserModel;
import com.stage.crudApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }
    public UserModel saveUser(UserModel newUser){
        UserModel user= userRepository.save(newUser);
        return user;
    }

    @Override
    public Optional<UserModel> findUserById(int id) {
        return Optional.empty();
    }

    @Override
    public UserModel findByUserName(String username) {
        return null;
    }
    public void autoLogin(String pseudo, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(pseudo);
        UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
    }

}
