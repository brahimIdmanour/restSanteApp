package com.stage.crudApi.controller;

import com.stage.crudApi.models.PatientModel;
import com.stage.crudApi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PreAuthorize("hasRole('ROLE_MEDECIN') or hasRole('ROLE_INFERMIER')")
    @GetMapping("/patients")
    public ResponseEntity<List<PatientModel>> getAllPatients(){
        return ResponseEntity.ok().body(patientService.findAllPatients());
    }

    @GetMapping("/patients/owned")
    @PostFilter("filterObject.owner==authentication.name")
    public List<PatientModel> getPatients(){
        return patientService.findAllPatients();
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientModel> save(@RequestBody PatientModel newPatient, Authentication auth) {
        System.out.println(newPatient.getPrenom()+ " " +auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(newPatient));
    }
    @GetMapping("/patients/{id}")
    public  ResponseEntity<PatientModel> getPatientById(@PathVariable("id") int patientId){
        return ResponseEntity.ok().body(patientService.findPatientById(patientId).get());
    }
    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientModel> update(@PathVariable("id") int patientId, @RequestBody PatientModel newPatient){
        return ResponseEntity.ok().body(patientService.updatePatient(patientId, newPatient));
    }
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int patientId){
        patientService.deletePatient(patientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/patients/search")
    public ResponseEntity<?> userDetails(Authentication auth, @RequestParam("cname") String cName){
        System.out.println(auth.getName().toString());
        PatientModel patientByPrenom = patientService.findPatientByPrenom(cName);
        if (patientByPrenom == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("patient not found");
        }
        return ResponseEntity.ok().body(patientByPrenom);
    }
}
