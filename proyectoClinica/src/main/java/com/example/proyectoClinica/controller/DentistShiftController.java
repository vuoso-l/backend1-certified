package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.controller.daos.impl.DentistShiftDaoH2;
import com.example.proyectoClinica.domain.Dentist;
import com.example.proyectoClinica.domain.DentistShift;
import com.example.proyectoClinica.servicies.DentistShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistShift")
public class DentistShiftController {
    private DentistShiftService dentistShiftService = new DentistShiftService(new DentistShiftDaoH2());

    @PostMapping()
    public ResponseEntity<DentistShift> dentistShiftRegister(DentistShift dentistShift) throws Exception {
        return ResponseEntity.ok(dentistShiftService.registerDentistShift(dentistShift));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentistShift(@PathVariable Long id) {
        ResponseEntity res = null;
        if (dentistShiftService.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            dentistShiftService.deleteDentistShift(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistShift> findDentistShift(Long id) {
        return ResponseEntity.ok(dentistShiftService.findOneById(id));
    }

    @GetMapping()
    public List<DentistShift> findAllDentistShifts() {
        return dentistShiftService.findAll();
    }

}
