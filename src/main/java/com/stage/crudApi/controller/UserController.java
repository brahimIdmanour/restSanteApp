package com.stage.crudApi.controller;

import com.stage.crudApi.models.UserModel;
import com.stage.crudApi.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_MEDECIN') or hasRole('ROLE_INFERMIER')")
    @GetMapping("/users")
    public List<UserModel> getAllUsers(Authentication authentication){
        return userService.findAllUsers();
    }
    @PostMapping("/user")
    public ResponseEntity<UserModel> save(@RequestBody UserModel newUser, Authentication auth){
        System.out.println(newUser.getPseudo()+" " +auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(newUser));
    }

    @PreAuthorize("@userSecurity.hasUserId(authentication,#userId)")
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("userId") int userId, Authentication authentication){
        return ResponseEntity.ok().body(userService.findUserById(userId).get());
    }
    @GetMapping("/users/search")
    @PostAuthorize("returnObject.body.userName==authenticated.user")
    public ResponseEntity<UserModel> userDetails(Authentication authentication, @RequestParam("came") String cName) throws Exception{
        System.out.println(authentication.getName().toString());
        UserModel byUserName = userService.findByUserName(cName);
        if (byUserName == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
        return  ResponseEntity.ok().body(byUserName);
    }
}
