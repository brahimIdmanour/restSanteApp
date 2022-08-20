package com.stage.crudApi.repository;

import com.stage.crudApi.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, Integer> {
    UserModel findByPseudo(String pseudo);
}
