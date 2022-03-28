package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.controller.daos.impl.PatientDaoH2;
import com.example.proyectoClinica.domain.Address;
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

}
