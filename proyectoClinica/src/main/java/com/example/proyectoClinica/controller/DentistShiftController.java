package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.repository.daos.impl.DentistShiftDaoH2;
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

    @PostMapping("/register")
    public ResponseEntity<DentistShift> dentistShiftRegister(@RequestBody DentistShift dentistShift) throws Exception {
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
    public ResponseEntity<DentistShift> findDentistShift(@PathVariable Long id) {
        return ResponseEntity.ok(dentistShiftService.findOneById(id));
    }

    @GetMapping()
    public List<DentistShift> findAllDentistShifts() {
        return dentistShiftService.findAll();
    }

    @PutMapping()
    public ResponseEntity<DentistShift> updateDentistShift(@RequestBody DentistShift dentistShift) {
        ResponseEntity<DentistShift> response = null;

        if (dentistShift.getId() != null && dentistShiftService.findOneById(dentistShift.getId()) != null)
            response = ResponseEntity.ok(dentistShiftService.updateDentistShift(dentistShift));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

}
