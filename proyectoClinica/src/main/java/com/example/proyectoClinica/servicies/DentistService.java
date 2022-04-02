package com.example.proyectoClinica.servicies;

import com.example.proyectoClinica.repository.IDao;
import com.example.proyectoClinica.domain.Dentist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {
    private IDao<Dentist> dentistDao;

    public DentistService() {

    }

    public DentistService(IDao<Dentist> dentistDao) {
        this.dentistDao = dentistDao;
    }

    public void setDentistDao(IDao<Dentist> dentistDao) {
        this.dentistDao = dentistDao;
    }

    public Dentist registerDentist(Dentist dentist) throws Exception {
        dentistDao.register(dentist);
        return dentist;
    }

    public void deleteDentist(Long id) {
        dentistDao.delete(id);
    }

    public Dentist findOneById(Long id) {
        return dentistDao.findOneById(id);
    }

    public List<Dentist> findAll() {
        return dentistDao.findAll();
    }

    public Dentist updateDentist(Dentist dentist) {
        return dentistDao.update(dentist);
    }
}
