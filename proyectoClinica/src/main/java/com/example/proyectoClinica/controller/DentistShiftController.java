package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.impl.DentistShiftDaoH2;
import com.example.proyectoClinica.domain.DentistShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistShift")
public class DentistShiftController {

    @Autowired
    private DentistShiftDaoH2 dentistShiftRepository;

    @PostMapping("/register")
    public ResponseEntity<DentistShift> dentistShiftRegister(@RequestBody DentistShift dentistShift) throws Exception {
        return ResponseEntity.ok(dentistShiftRepository.register(dentistShift));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentistShift(@PathVariable Long id) {
        ResponseEntity res = null;
        if (dentistShiftRepository.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            dentistShiftRepository.delete(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistShift> findDentistShift(@PathVariable Long id) {
        return ResponseEntity.ok(dentistShiftRepository.findOneById(id));
    }

    @GetMapping()
    public List<DentistShift> findAllDentistShifts() {
        return dentistShiftRepository.findAll();
    }

    @PutMapping()
    public ResponseEntity<DentistShift> updateDentistShift(@RequestBody DentistShift dentistShift) {
        ResponseEntity<DentistShift> response = null;

        if (dentistShift.getId() != null && dentistShiftRepository.findOneById(dentistShift.getId()) != null)
            response = ResponseEntity.ok(dentistShiftRepository.update(dentistShift));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
