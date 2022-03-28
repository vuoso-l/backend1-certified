package com.example.proyectoClinica.controller;

import com.example.proyectoClinica.controller.daos.impl.DentistDaoH2;
import com.example.proyectoClinica.domain.Dentist;
import com.example.proyectoClinica.servicies.DentistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {
    private DentistService dentistService = new DentistService(new DentistDaoH2());

    @PostMapping()
    public ResponseEntity<Dentist> dentistRegister(Dentist dentist) throws Exception {
        return ResponseEntity.ok(dentistService.registerDentist(dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentist(@PathVariable Long id) {
        ResponseEntity res = null;
        if (dentistService.findOneById(id) == null){
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            dentistService.deleteDentist(id);
            res = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findDentist(Long id) {
        return ResponseEntity.ok(dentistService.findOneById(id));
    }

    @GetMapping()
    public List<Dentist> findAllDentists() {
        return dentistService.findAll();
    }

}
