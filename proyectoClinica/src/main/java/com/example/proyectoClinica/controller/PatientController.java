package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.daos.impl.PatientDaoH2;
import com.example.proyectoClinica.domain.Patient;
import com.example.proyectoClinica.servicies.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private PatientService patientService = new PatientService(new PatientDaoH2());

    @PostMapping("/register")
    public ResponseEntity<Patient> patientRegister(@RequestBody Patient patient) throws Exception {
        return ResponseEntity.ok(patientService.registerPatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        ResponseEntity res = null;
        if (patientService.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            patientService.deletePatient(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Patient> findPatient(@PathVariable String email) {
        return ResponseEntity.ok(patientService.findOneByEmail(email));
    }

    @GetMapping()
    public List<Patient> findAllPatients() {
        return patientService.findAll();
    }

    @PutMapping()
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        ResponseEntity<Patient> response = null;

        if (patient.getId() != null && patientService.findOneById(patient.getId()) != null)
            response = ResponseEntity.ok(patientService.updatePatient(patient));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
