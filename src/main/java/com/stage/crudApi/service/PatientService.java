package com.stage.crudApi.service;

import com.stage.crudApi.models.PatientModel;
import com.stage.crudApi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService{
    @Autowired
    private PatientRepository patientRepository;

    public PatientModel savePatient(PatientModel newPatient){
        PatientModel patien1 = patientRepository.save(newPatient);
        return patien1;
    }
    public  PatientModel updatePatient(int id, PatientModel patient2){
        Optional<PatientModel> retrivedPatient = patientRepository.findById(id);
        if (retrivedPatient == null )
            try {
                throw new Exception("not found");
            }catch (Exception e) {
                e.printStackTrace();
            }
        patientRepository.save(patient2);
        return patientRepository.findById(id).get();
    }
    @Override
    public List<PatientModel> findAllPatients() {
        return patientRepository.findAll();
    }
    public PatientModel deletePatient(int patientID) {
        Optional<PatientModel> founded = patientRepository.findById(patientID);
        if (founded == null )
            try {
                throw new Exception("not found");
            }catch (Exception e) {
                e.printStackTrace();
            }
        patientRepository.deleteById(patientID);
        return founded.get();
    }
    @Override
    public Optional<PatientModel> findPatientById(int id) {
        return patientRepository.findById(id);
    }

    @Override
    public PatientModel findPatientByPrenom(String prenom) {
        PatientModel byPrenom = patientRepository.findByPrenom(prenom);
        return byPrenom;
    }
}
