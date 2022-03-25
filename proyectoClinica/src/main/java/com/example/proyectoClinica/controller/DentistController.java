package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.domain.Dentist;
import com.example.proyectoClinica.servicies.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {
    @Autowired
    private DentistService dentistService;

    @PostMapping()
    public Dentist dentistRegister(Dentist dentist) throws Exception {
        return dentistService.registerDentist(dentist);
    }

    @GetMapping("/{id}")
    public Dentist findDentist(Long id) {
        return dentistService.findOneById(id);
    }

    @GetMapping()
    public List<Dentist> findAllDentists() {
        return dentistService.findAll();
    }
}
