package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.domain.Patient;
import com.example.proyectoClinica.servicies.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping()
    public Patient patientRegister(Patient patient) throws Exception {
        return patientService.registerPatient(patient);
    }

    @GetMapping("/{email}")
    public Patient findPatient(String email) {
        Patient patient = patientService.findOneByEmail(email);
        return patient;
    }

    @GetMapping()
    public List<Patient> findAllPatients() {
        return patientService.findAll();
    }
}
