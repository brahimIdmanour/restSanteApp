package com.stage.crudApi.service;

import com.stage.crudApi.models.PatientModel;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    public List<PatientModel> findAllPatients();

    public Optional<PatientModel> findPatientById(int id);

    public PatientModel findPatientByPrenom(String prenom);
}
