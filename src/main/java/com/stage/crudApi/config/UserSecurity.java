package com.stage.crudApi.config;

import com.stage.crudApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {

    @Autowired
    UserRepository userRepository;

    public boolean hasUsedId(Authentication authentication, Integer userId){
        int id = userRepository.findByPseudo(authentication.getName()).getId();
        if (id==userId)
            return true;
        return false;
    }
}
