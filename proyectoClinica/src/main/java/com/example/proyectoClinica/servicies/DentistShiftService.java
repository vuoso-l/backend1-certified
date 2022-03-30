package com.example.proyectoClinica.servicies;

import com.example.proyectoClinica.repository.daos.IDao;
import com.example.proyectoClinica.domain.DentistShift;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistShiftService {
    private IDao<DentistShift> dentistShiftDao;

    public DentistShiftService() {

    }

    public DentistShiftService(IDao<DentistShift> dentistShiftDao) {
        this.dentistShiftDao = dentistShiftDao;
    }

    public void setDentistShiftDao(IDao<DentistShift> dentistShiftDao) {
        this.dentistShiftDao = dentistShiftDao;
    }

    public DentistShift registerDentistShift(DentistShift dentistShift) throws Exception {
        dentistShiftDao.register(dentistShift);
        return dentistShift;
    }

    public void deleteDentistShift(Long id) {
        dentistShiftDao.delete(id);
    }

    public DentistShift findOneById(Long id) {
        return dentistShiftDao.findOneById(id);
    }

    public List<DentistShift> findAll() {
        return dentistShiftDao.findAll();
    }

    public DentistShift updateDentistShift(DentistShift dentistShift) {
        return dentistShiftDao.update(dentistShift);
    }
}
