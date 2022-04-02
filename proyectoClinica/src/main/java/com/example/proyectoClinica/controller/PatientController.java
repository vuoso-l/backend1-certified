package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.impl.PatientDaoH2;
import com.example.proyectoClinica.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientDaoH2 patientRepository;

    @PostMapping("/register")
    public ResponseEntity<Patient> patientRegister(@RequestBody Patient patient) throws Exception {
        return ResponseEntity.ok(patientRepository.register(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        ResponseEntity res = null;
        if (patientRepository.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            patientRepository.delete(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Patient> findPatient(@PathVariable String email) {
        return ResponseEntity.ok(patientRepository.findOneByEmail(email));
    }

    @GetMapping()
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @PutMapping()
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        ResponseEntity<Patient> response = null;

        if (patient.getId() != null && patientRepository.findOneById(patient.getId()) != null)
            response = ResponseEntity.ok(patientRepository.update(patient));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
