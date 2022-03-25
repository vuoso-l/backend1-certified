package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.domain.DentistShift;
import com.example.proyectoClinica.servicies.DentistShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dentistShift")
public class DentistShiftController {
    @Autowired
    private DentistShiftService dentistShiftService;

    @PostMapping()
    public DentistShift dentistShiftRegister(DentistShift dentistShift) throws Exception {
        return dentistShiftService.registerDentistShift(dentistShift);
    }

    @GetMapping("/{id}")
    public DentistShift findDentistShift(Long id) {
        return dentistShiftService.findOneById(id);
    }

    @GetMapping()
    public List<DentistShift> findAllDentistShifts() {
        return dentistShiftService.findAll();
    }
}
