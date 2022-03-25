package com.example.proyectoClinica.servicies;

import com.example.proyectoClinica.controller.daos.IDao;
import com.example.proyectoClinica.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private IDao<Patient> patientDao;

    public PatientService() {

    }

    public PatientService(IDao<Patient> patientDao) {
        this.patientDao = patientDao;
    }

    public void setPatientDao(IDao<Patient> patientDao) {
        this.patientDao = patientDao;
    }

    public Patient registerPatient(Patient patient) throws Exception {
        patientDao.register(patient);
        return patient;
    }

    public void deletePatient(Long id) {
        patientDao.delete(id);
    }

    public Patient findOneByEmail(String email) {
        return patientDao.findOneByEmail(email);
    }

    public List<Patient> findAll() {
        return patientDao.findAll();
    }
}
