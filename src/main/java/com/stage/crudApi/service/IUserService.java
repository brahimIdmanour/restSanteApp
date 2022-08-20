package com.stage.crudApi.service;

import com.stage.crudApi.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserModel> findAllUsers();

    public Optional<UserModel> findUserById(int id);

    public UserModel findByUserName(String username);
}
