package com.stage.crudApi.service;

import com.stage.crudApi.models.UserDetailsImpl;
import com.stage.crudApi.models.UserModel;
import com.stage.crudApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByPseudo(username);
        if (user == null){
            System.out.println("exception thrown");
            throw new UsernameNotFoundException(username + "notfound");
        }
        return new UserDetailsImpl(user);
    }
}
