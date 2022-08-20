package com.stage.crudApi.repository;

import com.stage.crudApi.models.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<PatientModel, Integer> {
    PatientModel findByPrenom(String prenom);
}
