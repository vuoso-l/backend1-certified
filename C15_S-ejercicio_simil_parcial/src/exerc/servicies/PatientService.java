package exerc.servicies;

import exerc.daos.IDao;
import exerc.models.Patient;

import java.util.List;

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

    public Patient findOne(Long id) {
        return patientDao.findOne(id);
    }

    public List<Patient> findAll() {
        return patientDao.findAll();
    }
}
